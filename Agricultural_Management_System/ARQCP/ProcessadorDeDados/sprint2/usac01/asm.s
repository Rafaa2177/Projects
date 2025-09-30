.section .text
    .global extract_token
extract_token:
    movl $0, %r9d
    movq %rsi, %r8
    movl $0, (%rdx)
    input_cc:
        movb (%rdi), %cl
        cmpb $0, %cl
        je extract_end

        cmpl $-1, %r9d
        je create_value
        
        cmpl $-2, %r9d
        je skip

        create_token:
            cmpb %cl, (%r8)
            je check_token
            movq %rsi, %r8 
            movl $-2, %r9d
            jmp input_inc

            check_token:
                cmpb $0x3A, (%r8)
                je token_end

                incq %r8

                cmpb $0x3A, %cl
                je token_end


                jmp input_inc

            token_end:
                movl $-1, %r9d
                jmp input_inc
        
        create_value:
            cmpb $0x23, %cl
            je end

            cmpb $0x2E, %cl
            je input_inc

            movsbl %cl, %ecx
            subl $48, %ecx
            movl (%rdx), %eax
            imull $10, %eax

            addl %ecx, %eax
            movl %eax, (%rdx)

            jmp input_inc

        skip:
            cmpb $0x23, %cl
            jne input_inc

            movl $0, %r9d

        input_inc:
            incq %rdi
            jmp input_cc

extract_end:
    cmpl $0, (%rdx)
    jne end
    movl $0x55555555, (%rdx)

    end:
        ret
