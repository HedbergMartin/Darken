# Test program: j, jr

    addi    $t0, $zero, 64
    addi    $t1, $zero, 20
    or      $s0, $t0, $t1
    jr      $t1
    nop
jump_here:
    sub     $s1, $t1, $s0
    nop
    nop
    add     $s2, $t0, $s0
    j       jump_here