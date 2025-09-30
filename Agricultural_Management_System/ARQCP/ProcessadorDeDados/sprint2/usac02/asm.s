.section .text
.global enqueue_value

enqueue_value:
    # Prologue
    pushq %rbp
    movq %rsp, %rbp

    subl $1, %esi           # Subtrai 1 do número de elementos para comparações com read e write
    movl (%rdx), %eax       # Move o valor de Read para um registrador temporário
    movl (%rcx), %r11d      # Move o valor de Write para um registrador temporário

    cmpl (%rcx), %esi       # Compara Write com o número de elementos
    je bufferFull           # Se for igual, salta para bufferFull

    cmpl (%rcx), %eax       # Compara Write com Read
    jg move                 # Se Read for maior, salta

    call writeValue         # Chama a função writeValue
    jmp end

move:
    cmpl (%rdx), %esi           # Compara Read com o número de elementos
    je toBegin                  # Se for igual, salta

    subl $1, %eax               # Subtrai 1 do valor provisório de Read
    cmpl (%rcx), %eax           # Compara o valor provisório de Read com Write
    jg moveHead                 # Se o valor provisório de Read for maior, salta para moveHead
                                # Senão, são iguais (menor não pode ser), então ajusta o tail também

    call writeValue             # Chama a função writeValue
    jmp end

toBegin:
    call writeValue             # Chama a função writeValue
    movl $0, (%rdx)             # Move Read para 0
    jmp end

moveHead:
    call writeValue             # Chama a função writeValue

bufferFull:
    call writeValue             # Chama a função writeValue
    movl $0, (%rcx)             # Move Write para 0
    cmpl (%rcx), %eax           # Compara Write com Read
    jg end                      # Se Read for maior, termina
                                # Se Read for menor
    addl $1, (%rdx)             # Adiciona 1 ao Read

end:
    # Epilogue
    movq %rbp, %rsp
    popq %rbp
    ret

# Função writeValue
writeValue:
    movl %r8d, (%rdi, %r11, 4)  # Coloca o valor no local de Write
    addl $1, (%rcx)             # Adiciona 1 ao Write
    ret
