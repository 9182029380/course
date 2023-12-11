package com.CodeWithSharath.CertificationCourses.controller.impl;

import com.CodeWithSharath.CertificationCourses.Entity.Courses;
import com.CodeWithSharath.CertificationCourses.mapper.CoursesMapper;
import com.CodeWithSharath.CertificationCourses.service.CoursesService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CoursesControllerImplTest {
    //TODO: create the data Test generator class CoursesBuilder
    private static final String ENDPOINT_URL = "/coursess";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private CoursesControllerImpl coursesController;
    @MockBean
    private CoursesService coursesService;
    @MockBean
    private CoursesMapper coursesMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.coursesController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(coursesMapper.asDTOList(ArgumentMatchers.any())).thenReturn(CoursesBuilder.getListDTO());

        Mockito.when(coursesService.findAll()).thenReturn(CoursesBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(coursesMapper.asDTO(ArgumentMatchers.any())).thenReturn(CoursesBuilder.getDTO());

        Mockito.when(coursesService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(CoursesBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(coursesService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(coursesService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(coursesMapper.asEntity(ArgumentMatchers.any())).thenReturn(CoursesBuilder.getEntity());
        Mockito.when(coursesService.save(ArgumentMatchers.any(Courses.class))).thenReturn(CoursesBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(CoursesBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(coursesService, Mockito.times(1)).save(ArgumentMatchers.any(Courses.class));
        Mockito.verifyNoMoreInteractions(coursesService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(coursesMapper.asEntity(ArgumentMatchers.any())).thenReturn(CoursesBuilder.getEntity());
        Mockito.when(coursesService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(CoursesBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(CoursesBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(coursesService, Mockito.times(1)).update(ArgumentMatchers.any(Courses.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(coursesService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(coursesService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(coursesService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(coursesService);
    }