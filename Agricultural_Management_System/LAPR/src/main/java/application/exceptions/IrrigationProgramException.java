package application.exceptions;

import java.time.LocalDate;

public class IrrigationProgramException extends RuntimeException{

    //Exceção para caso nao haja programa de rega dentro da data especificada

    public IrrigationProgramException(LocalDate date) {
        super("Im sorry, there is no irrigation program found in the date  " + date + "!");
    }
}


