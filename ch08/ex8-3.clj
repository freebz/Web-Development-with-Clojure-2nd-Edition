;; Handling the Upload

(ImageIO/write
 (scale (ImageIO/read (io/input-stream "image.jpg")) 0.5 150 150)
 "jpeg"
 (File. "scaled.jpg"))


(ImageIO/write
 (scale-image (io/input-stream "image.jpg") thumb-size)
 "jpeg"
 (File. "scaled.jpg"))
