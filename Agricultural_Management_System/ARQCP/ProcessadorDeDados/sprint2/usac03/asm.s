.section .data

.section .text
    .global enqueue_value
    enqueue_value:

    # Params
    # int* array - %rdi # Não utilizado
    # int length - %esi # Não utilizado
    # int* read - %rdx # Não utilizado
    # int* write - %rcx
    # int value - %r8d

    # Salva o valor em %r8d na posição apontada por %rcx
    movl %r8d, (%rcx)
    ret

