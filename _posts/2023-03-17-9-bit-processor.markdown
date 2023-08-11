---

layout: default
modal-id: 5
title: Design ISA and Processor
date: 2023-03-17
img: architecture-diagram.svg
alt: Simplified architecture diagram of the proccessor I designed
project-date: Winter 2023
# client: Start Bootstrap
category: School Project
link: https://github.com/abclop99/cse-141l-project
link-text: github.com
description: |
    ## Requirements
    The requirements for this project were to design an ISA
    and processor that
    - Separate instruction and data memory
      - 9 bit instructions
      - 8 bit data
    - Register file (or equivilant)
      - Can only write to one register at a time
      - Can only read 2 data registers per cycle
      - No more than 16 registers
    - No manual loop unrolling; need branching and logic
    - Simple instructions (Comparable complexity to ARMsim)
    - No massive look-up tables to map instructions a wider ISA
    
    and write the following programs for it:
    - Encode a message with Hamming code using 2-word blocks
    - Decode messages encoded with Hamming code as in the first
    program, and mark error status
    - Matching and counting patterns in a multi-byte message
      - Counting total occurences
      - Ocurrences within a single byte
      - Number of bytes containing pattern

    ## ISA Design
    
    When designing the ISA for the processor, my main goal was for
    the processor to be simple and easy to design and the code for
    the programs to be easy to write. In order to do this, I wanted
    the ISA to support a larger number of opcodes, enough registers,
    and some immediates. 


    ### ISA architectures considered

    #### Register-Register

    Most modern ISAs use a Register-Register architecture with
    a significantly larger instruction size.
    Since the instructions must fit in 9 bits, a register-register
    architecture with 1 source register and 1 source/destination
    register would take a large number of bits to be able to address
    a reasonable number of bits, leaving not enough bits for
    either the opcode or register addresses or both to write simple
    code for the programs.

    #### Accumulator with registers

    My ISA is an accumulator-register architecture, with 8 registers
    and an accumulator. The instructions consist of a 5-bit opcode
    for 32 instructions, 1 bit to toggle using a
    register or an immediate, and a 3-bit register/immediate.

    This allows for both more than enough opcodes and enough registers
    to avoid using data memory for storing intermediate values.
    It also simplifies the data paths and the control signals in the
    processor since data only needs to be sent through a few fixed paths.
    

    ````
     0   1   2   3   4 | 5 | 6   7   8
    -------------------|---|------------
             op        | i |  reg/val
    ````

    Instruction format:
    - 5-bit opcodes allow for up to 32 different instructions, which is more than enough
    - 3 bits for register address allow for up to 8 registers, which can be limiting, but is enough
    to use without frequently using data memory.
    - 1 bit to toggle the use of small immediates (0-7).
      - Immediates are very important for for using constant values used in many things including
      branching.

    ### CPU design

    ![Architecture Diagram](img/portfolio/architecture-diagram.svg "Architecture diagram for my processor design")

    ![Generated block diagram](img/portfolio/block-diagram.svg "Generated block diagram of my processor design")

    ## Slides:

    <embed src="/files/portfolio/isa-processor-design/video-slides.pdf" type="application/pdf" style="width:100%; height: 50rem; max-height: 90%;">
---
