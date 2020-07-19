package me.jdding.kkaochat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jdding.kkaochat.application.of.classes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class NamseoulController {

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/kkao/api/v1",
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = "application/json")
    public HashMap<String, Object> callAPI(@RequestBody HashMap<String, Object> obj ){

        List<HashMap<String, Object>> outputs = new ArrayList<>();
        HashMap<String, Object> resultJson = new HashMap<>();
        HashMap<String, Object> template = new HashMap<>();
        HashMap<String, Object> simpletext = new HashMap<>();
        HashMap<String, Object> text = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        try{
            String s = mapper.writeValueAsString(obj);
            System.out.println(s);

            HashMap<String, Object> userRequest = (HashMap<String, Object>) obj.get("userRequest");
            String utterance = userRequest.get("utterance").toString().replaceAll("\n", "");

            if(utterance.contains("종뚱")){
                sb.append("안녕하세요 종뚱이에요~\n" +
                        "무엇을 도와드릴까요?");
            }
            switch(utterance){
                case "/학사일정":
                    sb.append("1. 수강신청\n2. 등록금 납부\n3. 미등록 일반휴학 접수마감\n" +
                            "4. 2학기 개강일\n5. 수강신청 변경\n6. 수강신청 철회");
                    break;
                case "/수강신청":

                    CourseGrade firstGrade = new CourseGrade(2020,8,20);
                    CourseGrade secondGrade = new CourseGrade(2020,8,19);
                    CourseGrade thirdGrade = new CourseGrade(2020,8,18);
                    CourseGrade fourthGrade = new CourseGrade(2020,8,17);
                    CourseGrade wholeGrade = new CourseGrade(2020,8,24);

                    sb.append("4학년 수강신청\n"
                            + fourthGrade.getYear()+"년 "+ fourthGrade.getMonth()+"월 "+
                            fourthGrade.getDayOfMonth()+"일 입니다.\n" +
                            "* 현재 "+ fourthGrade.getNumberOfDays()+"일 남았습니다.\n\n"
                            + "3학년 수강신청\n"
                            + thirdGrade.getYear()+"년 "+ thirdGrade.getMonth()+"월 " +
                            thirdGrade.getDayOfMonth()+"일 입니다.\n" +
                            "* 현재 "+ thirdGrade.getNumberOfDays()+"일 남았습니다.\n\n"
                            + "2학년 수강신청\n"
                            + secondGrade.getYear()+"년 "+ secondGrade.getMonth()+"월 "+
                            secondGrade.getDayOfMonth()+"일 입니다.\n" +
                            "* 현재 "+ secondGrade.getNumberOfDays()+"일 남았습니다.\n\n"
                            + "1학년 수강신청\n"
                            + firstGrade.getYear()+"년 "+ firstGrade.getMonth()+"월 "+
                            firstGrade.getDayOfMonth()+"일 입니다.\n" +
                            "* 현재 "+ firstGrade.getNumberOfDays()+"일 남았습니다.\n\n"
                            + "전체 학년/타과 수강신청"
                            + wholeGrade.getYear()+"년 "+ wholeGrade.getMonth()+"월 "+
                            wholeGrade.getDayOfMonth()+"일 입니다.\n" +
                            "* 현재 "+ wholeGrade.getNumberOfDays()+"일 남았습니다.");
                   break;
                case "/등록금":
                    LocalDate now = LocalDate.now();
                    LocalDate dateRegistration = LocalDate.of(2020, 8, 24);
                    long between = ChronoUnit.DAYS.between(now, dateRegistration);
                    sb.append("등록금 납부는 "+ dateRegistration.getYear()+"년 "
                            + dateRegistration.getMonth()+"월 "
                            + dateRegistration.getDayOfMonth()+"일 입니다.\n"
                            +"* 현재 "+ between+"일 남았습니다.");
                    break;
                case "/휴학접수":
                    LocalDate now1 = LocalDate.now();
                    LocalDate take_time_off = LocalDate.of(2020, 8, 28);
                    long between1 = ChronoUnit.DAYS.between(now1, take_time_off);
                    sb.append("일반 휴학 접수 마감은 " + take_time_off.getYear()+"년 "+take_time_off.getMonth()+"월"
                            + take_time_off.getDayOfMonth()+"일 입니다.\n"
                            +"현재 "+ between1+"일 남았습니다.");
                    break;
                case "/2학기 개강":
                    LocalDate now2 = LocalDate.now();
                    LocalDate star_of_class = LocalDate.of(2020, 8, 31);
                    long between2 = ChronoUnit.DAYS.between(now2, star_of_class);
                    sb.append("2학기 개강일은 " + star_of_class.getYear()+"년 "+star_of_class.getMonth()+"월"
                            + star_of_class.getDayOfMonth()+"일 입니다.\n"
                            +"* 현재 "+ between2+"일 남았습니다.");
                    break;
                case "/수강신청 변경":
                    LocalDate now3 = LocalDate.now();
                    LocalDate update_of_subject = LocalDate.of(2020, 8, 31);
                    long between3 = ChronoUnit.DAYS.between(now3, update_of_subject);
                    sb.append("수강신청 변경일은 " + update_of_subject.getYear()+"년 "+update_of_subject.getMonth()+"월"
                            + update_of_subject.getDayOfMonth()+"일 입니다.\n"
                            +"* 현재 "+ between3+"일 남았습니다.");
                    break;
                case "/수강신청 철회":
                    LocalDate now4 = LocalDate.now();
                    LocalDate withdraw_of_subject = LocalDate.of(2020, 8, 31);
                    long between4 = ChronoUnit.DAYS.between(now4, withdraw_of_subject);
                    sb.append("수강신청 철회는 " + withdraw_of_subject.getYear()+"년 "+withdraw_of_subject.getMonth()+"월"
                            + withdraw_of_subject.getDayOfMonth()+"일 입니다.\n"
                            +"* 현재 "+ between4+"일 남았습니다.");
                    break;
            }


            text.put("text", sb.toString());
            simpletext.put("simpleText", text);
            outputs.add(simpletext);
            template.put("outputs", outputs);
            resultJson.put("version","2.0");
            resultJson.put("template", template);

        }catch (Exception e){
            e.printStackTrace();
        }

        return resultJson;
    }

}
