package by.htp.teploukhava.testing.controller;

import by.htp.teploukhava.testing.entities.*;
import by.htp.teploukhava.testing.exception.QuestionService;
import by.htp.teploukhava.testing.serviceimpl.SubjectService;
import by.htp.teploukhava.testing.serviceimpl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 16.11.16.
 */

@Controller
@SessionAttributes({"subject", "test"})

@RequestMapping(value = "/admin")
public class AdminController {
    private final static int recordsPerPage = 2;

    private SubjectService subjectService;
    private TestService testService;
    private QuestionService questionService;

    @Autowired
    public AdminController(SubjectService subjectService, TestService testService,
                           QuestionService questionService) {
        this.subjectService = subjectService;
        this.testService = testService;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/subjects/{currentPage}")
    public String getSubjectPerPage(@PathVariable("currentPage") Integer currentPage, ModelMap modelMap) {
        List<SubjectDTO> subjectList = subjectService.findByPage(recordsPerPage, currentPage);
        long countPages = (long) Math.ceil((double) subjectService.countRecords() / recordsPerPage);
        List listPages = new ArrayList();
        int i = 1;
        while (i <= countPages) {
            listPages.add(i);
            i++;
        }
        modelMap.addAttribute("subjectList", subjectList);
        modelMap.addAttribute("listPages", listPages);
        return "/admin/subject";
    }

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public String getSubjectTests(@RequestParam(value = "subjectId") int subjectId,
                                  @RequestParam(value = "currentPage") int currentPage, ModelMap modelMap) {
        Subject subject = subjectService.find(subjectId);
        modelMap.addAttribute("subject", subject);
        List<Test> testList = testService.findSubjectTestByPage(subjectId, recordsPerPage, currentPage);
        long countPages = (long) Math.ceil((double) testService.countRecords(subjectId) / recordsPerPage);
        List listPages = new ArrayList();
        int i = 1;
        while (i <= countPages) {
            listPages.add(i);
            i++;
        }
        modelMap.addAttribute("testList", testList);
        modelMap.addAttribute("listPages", listPages);
        return "admin/test";
    }

    @RequestMapping(value = "/addsubject", method = RequestMethod.GET)
    public String addSubject(ModelMap modelMap) {
        modelMap.addAttribute("subject", new Subject());
        return "admin/addsubject";
    }

    @RequestMapping(value = "/savesubject", method = RequestMethod.POST)
    public String saveSubject(@ModelAttribute("subject") Subject subject, ModelMap modelMap) {
        subjectService.create(subject);
        return getSubjectPerPage(1, modelMap);
    }

    @RequestMapping(value = "/addtest", method = RequestMethod.GET)
    public String addTest(@ModelAttribute("subject") Subject subject, ModelMap modelMap) {
        Test test = new Test();
        test.setSubject(subject);
        List<Question> questionList = new ArrayList<Question>();
        test.setListQuestions(questionList);
        modelMap.addAttribute("test", test);
        return "admin/contenttest";
    }

    @RequestMapping(value = "/addquestion", method = RequestMethod.POST)
    public String addQuestion(@ModelAttribute("test") Test test, ModelMap modelMap) {
        List<Answer> answerList = new ArrayList<Answer>();
        Question question = new Question();
        for (int i = 0; i < 5; i++) {
            Answer answer = new Answer();
            answer.setQuestion(question);
            answerList.add(answer);
        }
        question.setListAnswer(answerList);
        //   question.setTest(test);
        modelMap.addAttribute("question", question);
        return "admin/question";
    }

    @RequestMapping(value = "/savequestion", method = RequestMethod.POST)
    public String saveQuestion(@ModelAttribute("test") Test test,
                               @ModelAttribute("question") Question question) {
        question.setTest(test);
        for (Answer answer : question.getListAnswer()) {
            answer.setQuestion(question);
        }
        List<Question> questionList = (List<Question>) test.getListQuestions();
        questionList.add(question);
        test.setListQuestions(questionList);
        if (test.getTestId() != 0) {
            return "admin/changetest";
        } else {
            return "admin/contenttest";
        }

    }

    @RequestMapping(value = "/savetest", method = RequestMethod.POST)
    public String saveTest(@ModelAttribute("test") Test test, HttpSession session,
                           ModelMap modelMap) {
        if (test.getTestId() != 0) {
            testService.update(test);
        } else {
            testService.create(test);
        }
        session.removeAttribute("test");
        return getSubjectTests(test.getSubject().getSubjectId(), 1, modelMap);
    }

    @RequestMapping(value = "/deletetest", method = RequestMethod.GET)
    public String deleteTest(@RequestAttribute("testId") int testId,
                             @ModelAttribute("subject") Subject subject,
                             ModelMap modelMap) {
        testService.delete(testId);
        return getSubjectTests(subject.getSubjectId(), 1, modelMap);
    }

    @RequestMapping(value = "/changetest", method = RequestMethod.GET)
    public String changeTest(@RequestAttribute("testId") int testId,
                             ModelMap modelMap) {
        Test test = testService.find(testId);
        modelMap.addAttribute("test", test);
        return "admin/changetest";
    }

    @RequestMapping(value = "/deletequestion", method = RequestMethod.GET)
    public String deleteQuestion(@RequestAttribute("questionId") int questionId,
                                 @ModelAttribute("subject") Subject subject,
                                 @ModelAttribute("test") Test test,
                                 ModelMap modelMap) {
        questionService.delete(questionId);
        return changeTest(subject.getSubjectId(), modelMap);
    }
}
