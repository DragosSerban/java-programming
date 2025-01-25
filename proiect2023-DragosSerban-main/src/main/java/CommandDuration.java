import java.time.Duration;

/*
    pt transformarea duratei din integer in formatul propriu-zis si invers se va utiliza design patterul
    Command, ce transforma requestul intr-un obiect cu ajutorul caruia se executa comanda propriu-zisa
 */
abstract class CommandDuration {
    public abstract void execute(TimeDuration time);
}

/*
    se seteaza formatul duratei (tip string)
 */
class CommandDurationFormat extends CommandDuration {
    public void execute(TimeDuration time) {
        time.setFormat();
    }
}

/*
    se seteaza nr de secunde al duratei (retinut intr-un int)
 */
class CommandDurationSeconds extends CommandDuration {
    public void execute(TimeDuration time) {
        time.setNoOfSeconds();
    }
}

class TimeDuration {
    private String formattedDuration;
    private Long noOfSeconds;

    TimeDuration(String formattedDuration) {
        this.formattedDuration = formattedDuration;
    }

    TimeDuration(Long noOfSeconds) {
        this.noOfSeconds = noOfSeconds;
    }

    public String getFormattedDuration() {
        return formattedDuration;
    }

    public Long getNoOfSeconds() {
        return noOfSeconds;
    }

    public void setFormat() {
        Duration duration = Duration.ofSeconds(noOfSeconds);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        if (hours == 0)
            formattedDuration = String.format("%02d:%02d", minutes, seconds);
        else
            formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void setNoOfSeconds() {
        Duration duration = Duration.parse(formattedDuration);
        noOfSeconds = duration.getSeconds();
    }
}
