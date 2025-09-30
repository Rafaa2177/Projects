package application.domain;

import java.security.PublicKey;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class IrrigationProg {
    private final LocalDate startData;
    private final String parcela;
    private final List<String> startTime;
    private final int durationOfIrrigation;
    private final String regularidade;

    private final String mix;
    private final int recorrencia;

    public IrrigationProg(List<String> startTime, LocalDate startData, String parcela, int durationOfIrrigation, String regularidade) {
        this.startData = startData;
        this.parcela = parcela;
        this.startTime = startTime;
        this.durationOfIrrigation = durationOfIrrigation;
        this.regularidade = regularidade;
        this.mix = " ";
        this.recorrencia = 0;
    }

    public IrrigationProg(List<String> startTime, LocalDate startData, String parcela, int durationOfIrrigation, String regularidade, String mix, int recorrencia) {
        this.startData = startData;
        this.parcela = parcela;
        this.startTime = startTime;
        this.durationOfIrrigation = durationOfIrrigation;
        this.regularidade = regularidade;
        this.mix = mix;
        this.recorrencia = recorrencia;
    }

    public int getDurationOfIrrigation() {
        return durationOfIrrigation;
    }
    public TemporalAmount getIrrigationTemporalAmount() {

        return Duration.ofMinutes(durationOfIrrigation);
    }

    public List<String> getStartTime() {
        return startTime;
    }

    public LocalDate getStartData() {
        return startData;
    }

    public String getParcela() {
        return parcela;
    }

    public String getRegularidade() {
        return regularidade;
    }

    public String getMix() {
        return mix;
    }



    public int getRecorrencia() {
        return recorrencia;
    }



    public boolean checkFertilization(LocalDate dataAtual, LocalDate dataInicio) {
        long diferencaEmDias = ChronoUnit.DAYS.between(dataInicio, dataAtual);
        if(recorrencia!=0){
            if (diferencaEmDias == 0) {
                return true;
            }
            return (diferencaEmDias % recorrencia == 0) && (diferencaEmDias >= recorrencia);
        }else{
            return false;
        }
    }


}
