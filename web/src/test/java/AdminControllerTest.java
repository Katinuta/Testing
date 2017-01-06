import by.htp.teploukhava.testing.controller.AdminController;
import by.htp.teploukhava.testing.entities.SubjectDTO;
import by.htp.teploukhava.testing.exception.QuestionService;
import by.htp.teploukhava.testing.serviceimpl.SubjectService;
import by.htp.teploukhava.testing.serviceimpl.TestService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Admin on 27.09.16.
 */
public class AdminControllerTest {
    ModelMap modelMapActual;
    ModelMap modelMapExpected;
    SubjectService subjectService;
    TestService testService;
    QuestionService questionService;
    AdminController adminController;

    @Before
    public void setUp() throws Exception {
        modelMapActual = new ModelMap();
        modelMapExpected=new ModelMap();
        subjectService = mock(SubjectService.class);
        testService = mock(TestService.class);
        questionService = mock(QuestionService.class);
        adminController = new AdminController(subjectService, testService, questionService);
    }

    @After
    public void tearDown() throws Exception {
        modelMapActual = null;
        adminController = null;
        modelMapExpected=null;
    }

    @Test
    public void getSubjectsPerPage() throws Exception {
        List<SubjectDTO> subjectList = new ArrayList<SubjectDTO>();
        subjectList.add(new SubjectDTO(2, "English"));
        subjectList.add(new SubjectDTO(5, "JavaEE"));
        modelMapExpected.addAttribute("subjectList",subjectList);
        when(subjectService.findByPage(2, 1)).thenReturn(subjectList);
        when(subjectService.countRecords()).thenReturn(2l);
        String pageActual = adminController.getSubjectPerPage(1, modelMapActual);
        assertEquals("/admin/subject", pageActual);
        assertSame(modelMapExpected.get("subjectList"),modelMapActual.get("subjectList"));
    }

}