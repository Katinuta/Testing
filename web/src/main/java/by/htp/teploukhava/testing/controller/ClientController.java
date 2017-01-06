package by.htp.teploukhava.testing.controller;

import by.htp.teploukhava.testing.entities.*;
import by.htp.teploukhava.testing.serviceimpl.ResultService;
import by.htp.teploukhava.testing.serviceimpl.SubjectService;
import by.htp.teploukhava.testing.serviceimpl.TestService;
import by.htp.teploukhava.testing.serviceimpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 16.11.16.
 */
@Controller
@RequestMapping(value = "/client")
@SessionAttributes({"user","subject","test"})
public class ClientController {

    private final static int  recordsPerPage =1;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private TestService testService;
    @Autowired
    private ResultService resultService;

    public ClientController(SubjectService subjectService,UserService userService,
                            TestService testService, ResultService resultService){
        this.subjectService=subjectService;
        this.userService=userService;
        this.testService= testService;
        this.resultService=resultService;
    }

    @RequestMapping(value="/subjects/{currentPage}")
    public String getSubjectPerPage(@PathVariable("currentPage") Integer currentPage,
                                    ModelMap modelMap){
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user= userService.findUserByLogin(userDetails.getUsername());
        List<SubjectDTO> listSubject=userService.findUserSubjectPage(user.getUserId(),currentPage,recordsPerPage);
        for (SubjectDTO subject:listSubject ) {
            System.out.println(subject.toString());
        }
        long countPages= (long) Math.ceil((double) userService.countUserSubject(user.getUserId())/recordsPerPage);
        List listPages = new ArrayList();
        int i=1;
        while(i<=countPages){
            listPages.add(i);
            i++;
        }
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("subjectList",listSubject);
        modelMap.addAttribute("listPages", listPages);
        return"client/subject";
    }
    @RequestMapping(value="/main", method = RequestMethod.GET)
    public String mainP(){
        return "client/main";
    }

    @RequestMapping(value = "/tests",method = RequestMethod.GET)
    public String getSubjectTests(@ModelAttribute("user") User user,
                                  @RequestParam(value = "subjectId") int subjectId,
                                  @RequestParam(value = "currentPage") int currentPage,ModelMap modelMap){
            Subject subject=subjectService.find(subjectId);
            modelMap.addAttribute("subject",subject);
        List<Test> testList= testService.findSubjectTestByPage(subjectId,recordsPerPage,currentPage);
        long countPages=(long) Math.ceil((double) testService.countRecords(subjectId)/recordsPerPage);
        List listPages = new ArrayList();
        int i=1;
        while(i<=countPages){
            listPages.add(i);
            i++;
        }
        modelMap.addAttribute("resultList",getResults(testList,user.getUserId()));
        modelMap.addAttribute("testList", testList);
        modelMap.addAttribute("listPages", listPages);
        return "client/test";
    }

    @RequestMapping(value = "/passtest",method = RequestMethod.GET)
    public String passTest( @RequestParam(value = "testId") int testId,ModelMap modelMap){
        Test test=testService.find(testId);
        for (Question question:test.getListQuestions()) {
            for (Answer answer:question.getListAnswer()) {
                answer.setRightAnswer(false);
            }
        }
        modelMap.addAttribute("test",test);
        return "client/contenttest";
    }

    @RequestMapping(value = "/checktest",method = RequestMethod.POST)
    public String checkTest(@ModelAttribute("test") Test test,
                            @ModelAttribute("user") User user,
                            ModelMap modelMap){
        int countRightQuestions=0;
        Test testRight=testService.find(test.getTestId());
        for (int i=0;i<testRight.getListQuestions().size();i++) {
            Question questionRight=testRight.getListQuestions().get(i);
            Question question=test.getListQuestions().get(i);
            int countRightAnswers=0;
            for(int j=0;j<questionRight.getListAnswer().size();j++){
                Answer answerRight=questionRight.getListAnswer().get(j);
                Answer answer=question.getListAnswer().get(j);
                if(answer.equals(answerRight)){
                    countRightAnswers++;
                }
            }
            if(countRightAnswers==questionRight.getListAnswer().size()){
                countRightQuestions++;
            }
            countRightAnswers=0;
        }
        int res=countRightQuestions*100/testRight.getListQuestions().size();
        Result result=new Result();
        result.setIdResult(new Result.IdResult(test.getTestId(),user.getUserId()));
        result.setResult(res);
        resultService.create(result);
        modelMap.addAttribute("result",result);
        return "client/result";
    }

    public List<Result> getResults(List<Test> testList,int userId){
        List<Result> resultList=new ArrayList<Result>();
        for (Test test:testList) {
            Result result=resultService.findResultByTestUser(test.getTestId(),userId);
            resultList.add(result);
        }
        return resultList;
    }
}
