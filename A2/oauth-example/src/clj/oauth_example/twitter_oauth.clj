(ns oauth-example.twitter-oauth
  (:require [oauth.client :as oauth]
            [config.core :refer [env]]))

(def request-token-uri
  "https://api.twitter.com/oauth/request_token")

(def access-token-uri
  "https://api.twitter.com/oauth/access_token")

(def authorize-uri
  "https://api.twitter.com/oauth/authenticate")

(def consumer
  (oauth/make-consumer (env :twitter-consumer-key)
                       (env :twitter-consumer-secret)
                       request-token-uri
                       access-token-uri
                       authorize-uri
                       :hmac-sha1))

(defn oauth-callback-uri
  "Generates the twitter oauth request callback URI"
  [{:keys [headers]}]
  (str (headers "x-forwarded-proto") "://" (headers "host") "/oauth/twitter-callback"))

(defn fetch-request-token
  "Fetches a request token."
  [request]
  (->> request
       oauth-callback-uri
       (oauth/request-token consumer)
       :oauth_token))

(defn fetch-access-token
  [request_token]
  (oauth/access-token consumer request_token (:oauth_verifier request_token)))

(defn auth-redirect-uri
  "Gets the URI the user should be redirected to when authenticating with twitter."
  [request-token]
  (str (oauth/user-approval-uri consumer request-token)))