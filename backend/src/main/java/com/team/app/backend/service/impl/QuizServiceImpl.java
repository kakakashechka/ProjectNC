package com.team.app.backend.service.impl;

import com.team.app.backend.dto.DefOptionDto;
import com.team.app.backend.dto.OptionDto;
import com.team.app.backend.dto.SeqOptionDto;
import com.team.app.backend.dto.QuestionDefAddDto;
import com.team.app.backend.dto.QuestionDto;
import com.team.app.backend.dto.QuestionOptAddDto;
import com.team.app.backend.dto.QuestionSeqAddDto;
import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.persistance.dao.OptionDao;
import com.team.app.backend.persistance.dao.QuestionDao;
import com.team.app.backend.persistance.dao.QuizDao;
import com.team.app.backend.persistance.model.*;
import com.team.app.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private OptionDao optionDao;



    @Override
    public Long addDefQuestion(QuestionDefAddDto questionDefAddDto) {
//        Question question = new Question();
//        question.setQuiz_id((long)questionDefAddDto.getQuiz_id());
//        question.setType(questionDefAddDto.getType());
//        question.setImage(questionDefAddDto.getImage());
//        question.setText(questionDefAddDto.getText());
//        question.setMax_points(questionDefAddDto.getMax_points());
//        question.setTime(questionDefAddDto.getTime());
//        Long id = questionDao.save(question);
//        System.out.println(id);
        Long id = addQuestion(questionDefAddDto);
        for (DefOptionDto defOptionDto: questionDefAddDto.getOptions()) {
            DefaultQuest defaultQuest=new DefaultQuest();
            defaultQuest.setAnswer(defOptionDto.getAnswer());
            defaultQuest.setImage(defOptionDto.getImage());
            defaultQuest.setQuest_id(id);
            optionDao.addDefaultOption(defaultQuest);
        }
        return id;

    }

    @Override
    public Long addQuestion(QuestionDto questionDto){
        Question question = new Question();
        question.setQuiz_id((long)questionDto.getQuiz_id());
        question.setType(questionDto.getType());
        question.setImage(questionDto.getImage());
        question.setText(questionDto.getText());
        if(questionDto.getMax_points()==null){
            question.setMax_points(10);
        }else{
            question.setMax_points(questionDto.getMax_points());
        }
        question.setTime(questionDto.getTime());
        return questionDao.save(question);
    }

    @Override
    public Long addOptQuestion(QuestionOptAddDto questionOptAddDto) {
//        Question question = new Question();
//        question.setQuiz_id((long)questionOptAddDto.getQuiz_id());
//        question.setType(questionOptAddDto.getType());
//        question.setImage(questionOptAddDto.getImage());
//        question.setText(questionOptAddDto.getText());
//        question.setMax_points(questionOptAddDto.getMax_points());
//        question.setTime(questionOptAddDto.getTime());
        Long id = addQuestion(questionOptAddDto);
        System.out.println(id);
        for (OptionDto optionDto: questionOptAddDto.getOptions()) {
            Option option=new Option();
            option.setIs_correct(optionDto.getIs_correct());
            option.setImage(optionDto.getImage());
            option.setQuest_id(id);
            option.setText(optionDto.getText());
            optionDao.addOption(option);
        }
        return id;
    }

    @Override
    public Long addSeqOptQuestion(QuestionSeqAddDto questionSeqAddDto) {
//        Question question = new Question();
//        question.setQuiz_id((long)questionSeqAddDto.getQuiz_id());
//        question.setType(questionSeqAddDto.getType());
//        question.setImage(questionSeqAddDto.getImage());
//        question.setText(questionSeqAddDto.getText());
//        question.setMax_points(questionSeqAddDto.getMax_points());
//        question.setTime(questionSeqAddDto.getTime());
        Long id = addQuestion(questionSeqAddDto);
        System.out.println(id);
        for (SeqOptionDto seqOptionDto: questionSeqAddDto.getOptions()) {
            SeqOption seqOption=new SeqOption();
            seqOption.setImage(seqOptionDto.getImage());
            seqOption.setQuest_id(id);
            seqOption.setSerial_num(seqOptionDto.getSerial_num());
            seqOption.setText(seqOptionDto.getText());
            optionDao.addSeqOption(seqOption);
        }
        return id;
    }

    @Override
    public void deleteQuiz(Long id) {

        quizDao.delete(id);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionDao.delete(id);
    }

    @Override
    public List<Quiz> getUserQuizes(Long id) {
        return quizDao.getByUserId(id);
    }

    @Override
    public List<Question> getQuizQuestion(Long id) {
        return questionDao.getQuizQusetions(id);
    }

    @Override
    public Question getQuestion(Long id) {
        return questionDao.getQuestion(id);
    }


    @Override
    public Long addQuiz(QuizAddDto quizAddDto) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        System.out.println((long)quizAddDto.getUser_id());
        Quiz quiz = new Quiz();
        quiz.setTitle(quizAddDto.getTitle());
        quiz.setDescription(quizAddDto.getDescription());
        quiz.setDate(date);
        quiz.setImage(quizAddDto.getImage());
        quiz.setUser_id((long)quizAddDto.getUser_id());
        quiz.setStatus_Id(new QuizStatus( 1L,"created"));
        return quizDao.save(quiz);
    }


    @Override
    public List<Quiz> getAllQuizes() {
        List<Quiz> list =  quizDao.getAll();
        System.out.println(list.size());
        return list;
    }

    @Override
    public Quiz getQuiz(Long id) {
        return quizDao.get(id);
    }
}
