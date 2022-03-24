---
layout: default
modal-id: 4
title: Image Merging using Laplacian Pyramids
date: 2021-08-05
img: image-merging.png
alt: Merging an image of an apple with an image of an orange
project-date: October 2021
# client: Start Bootstrap
category: Personal Project
link: https://github.com/abclop99/image-pyramid-merging
link-text: github.com
description: |
    This probram merges two images using Laplacian Pyramids.
    - Generates Laplacian pyramids for each image by taking the difference between layers in its Gaussian pyramid, merges each layer using a generated gradient, and reconstructs an image from the new Laplacian pyramid.
    - User interface created using [Qt](https://www.qt.io/)
      - Allows choosing any two images to merge and changing the gradient used to merge the images.
      - Resizes the second image if not the same size as the first.
      - Resizes the window to fit the images.
    - Uses OpenCV for image input and output
    - Written in C++
    - Inspired by [this video](https://www.youtube.com/watch?v=HfM9s2ehErE)
      - Fixes a mistake made in the video by allowing negative numbers in the Laplacian Pyramid layers.
---
