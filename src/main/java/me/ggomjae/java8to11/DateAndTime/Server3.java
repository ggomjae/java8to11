package me.ggomjae.java8to11.DateAndTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Server3 {
    public static void main(String[] args) throws InterruptedException {

        Date date = new Date();
        long time = date.getTime();

        // 인류용 시간
        System.out.println(date);
        // 기계용 시간이다. 1523425236 라는 시간이 나오면 이건 사람이 알지 못한다.
        System.out.println(time);

        /*
            이런식으로 하면 바뀐다. 즉, Mutable 하기에 안정하지않다.
            그래서 멀티쓰레드에는 안전하지 않다. 필기*
         */
        Thread.sleep(1000*2);
        Date after2Seconds = new Date();
        System.out.println(after2Seconds);
        after2Seconds.setTime(time);
        System.out.println(after2Seconds);
        ////////////////////////////////////////////////////////////////////

        /*
            LocalDate  Vs     LocalDateTime
            날짜 정보만 출력    날짜와 시간 정보 출력
         */

        // GregorianCalender -> ZoneDateTime , Instant -> zoneDateTime 등 서로 변환이 가능하다.
        // 이것을 레거시 API 지원이라고 한다. 과거에 있었던 것들과 상호변환.
        Date date1 = new Date();
        Instant instant = date1.toInstant();
        Date newDate = Date.from(instant);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        // ZoneId.systemDefault() 지금 현재 내 컴퓨터 시스템상 시간.
        ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from = GregorianCalendar.from(dateTime);

        // 이렇게 서울 시간도 가능
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);

        // 인류용 시간 - 만약 이것을 한국에서 미국에 배포한다. 그럼 미국 시간이 찍힌다는 그런 뜻이다.
        LocalDateTime now = LocalDateTime.now();
        // 이렇게 만들수도 있다.
        LocalDateTime birthday = LocalDateTime.of(1993,Month.FEBRUARY,18,0,0,0);
        now.plus(10, ChronoUnit.DAYS);

        /*
                   Period  Vs  Duration
         */
        // 며칠남았는지도 확인 가능 - 인류용
        LocalDate today = LocalDate.now();
        LocalDate thisbirthday = LocalDate.of(1993,Month.FEBRUARY,18);
        Period period = Period.between(today, thisbirthday);
        System.out.println(period.getDays());

        // 기계용 -- 여기서 중요한 것이 now1.plus() 하고 끝나면 아무일도 안일어나. plus1을 반환하는거야. LocalDateTime도 같아.
        Instant now1 = Instant.now();
        Instant plus1 = now1.plus(10,ChronoUnit.SECONDS);
        Duration between = Duration.between(now1,plus1);
        System.out.println(between.getSeconds());

        // LocalDateTime date 해서 써도되지만 원하는 Format이 있는경우 DateTimeFormatter를 쓰면된다.
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(MMddyyyy));

        // 결과는 1993-02-18 이 나온다.
        LocalDate parse = LocalDate.parse("02/18/1993", MMddyyyy);
        System.out.println(parse);
    }
}
