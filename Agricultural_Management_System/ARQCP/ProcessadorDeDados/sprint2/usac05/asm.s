.section .text
    .global mediana

mediana:
    movl %esi, %eax
    movl $2, %ecx
    cdq
    idivl %ecx

end:
    movl (%rdi,%rax,4), %eax
    ret
