---

layout: default
modal-id: 5
title: Cloth Simulation
date: 2023-03-01
img: cloth-simulation.webp
alt: Cloth simulation demonstration video
project-date: Winter 2023
# client: Start Bootstrap
category: School Project
link: https://github.com/abclop99/cloth_simulation
link-text: github.com
description: |
    This was a cloth simulation with simple aerodynamics and collision
    and friction
    against a flat ground. It supports rotating the camera with the arrow
    keys, zooming with `a` and `z`, moving the cloth's anchor points with
    the mouse, and changing the wind speed/direction using the mouse while
    pressing `w`. It supports loading cloths of different sizes using a
    command line argument and loading different arbitrary configs from
    `JSON` files.

    This project was written in Rust using WGPU[^1] for graphics. Shaders were
    written in [WGSL](https://www.w3.org/TR/WGSL/)

    The cloth consists of a grid of nodes with springs horizontally and
    vertically between the nodes and along one diagonal. There are also bending
    springs between nodes in a slightly larger grid.

    [^1]: Thanks [Learn Wgpu](https://sotrh.github.io/learn-wgpu/) for teaching how to use WGPU
---
