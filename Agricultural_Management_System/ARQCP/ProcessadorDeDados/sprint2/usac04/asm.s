.section .data

.section .text
    .global sort_array

sort_array:
    movl $0, %eax
primeiroLoop:
    cmpl $0, %esi
    je end

    subl $1, %esi
    movl %esi, %eax
    jmp segundoLoop

segundoLoop:
    cmpl $0, %eax
    je end2

    movl (%rdi), %ecx
    movl %ecx, %r8d
    addq $4, %rdi

    movl (%rdi), %ecx
    movl %ecx, %r9d
    cmpl %r8d, %r9d
    jl change

    decl %eax
    jmp segundoLoop

change:
    subq $4, %rdi
    movl %r9d, (%rdi)
    addq $4, %rdi

    movl %r8d, (%rdi)
    decl %eax
    jmp segundoLoop

end2:
    movl %esi, %eax
    jmp reset

reset:
    cmpl $0, %eax
    je primeiroLoop
    subq $4, %rdi
    decl %eax
    jmp reset

end:
    ret
