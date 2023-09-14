---

layout: default
modal-id: 8
title: "Nvidia Jetson: Face Autofocus"
date: 2023-09-12
img: face-autofocus.webp
alt: TODO
project-date: September 2023
# client: Start Bootstrap
category: Personal Project
link: https://github.com/abclop99/MIPI_Camera/blob/mp_face-autofocus/Jetson/IMX219/mediapipe_focus.py
link-text: github.com
description: |
    This program is ArduCam's `FocuserExample.py` modified to autofocus on any
    detected faces, if any, when `f` is pressed.
    It runs on an Nvidia Jetson Orin NX 16G.

    - It uses Mediapipe's face detection to acquire face bounding boxes.
    - Modified `JetsonCamera.py` to accept a function to modify the displayed image
    - Autofocus on separate thread from UI and camera
        - Coordinated with `threading.Event`
    - Uses a Laplacian evaluation metric, weighted significantly in favor of the
        bounding boxes and a linear search for the maximum.
---
