package com.dadingcoding.web.service;

import com.dadingcoding.web.response.ClassScheduleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorService {
    public List<ClassScheduleResponse> getClassSchedule() {
        return List.of();
    }

    public List<ListAnswerResponseDto> getMenteeQuestions() {
        List<Question> questions = questionRepository.findAll();

        return questions.stream()
                .map(question -> new ListAnswerResponseDto(
                        question,
                        question.getAnswers().stream().map(AnswerDto::toDto).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public void answerMenteeQuestion(Long questionId, AnswerDto answerDto) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Invalid question ID"));
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(answerDto.getAnswer());
        answer.setCreatedAt(LocalDateTime.now());
        answerRepository.save(answer);

        public void addPreMentorBoardPost(AddPostRequest request) {
            // 예비멘토 게시판 글 추가 로직 구현
            Post post = new Post();
            post.setTitle(request.getTitle());
            post.setContent(request.getContent());
            post.setCreatedAt(LocalDateTime.now());
            postRepository.save(post);
        }

        public void deletePreMentorBoardPost(long postId) {
            // 예비멘토 게시판 글 삭제 로직 구현
            postRepository.deleteById(postId);
        }

        public List<Post> getPreMentorBoardPosts() {
            // 예비멘토 게시판 목록 조회 로직 구현
            return postRepository.findAll();
        }

        public void addOrUpdateReport(UpdateReportRequest request) {
            Report report = new Report();
            report.setTitle(request.getTitle());
            report.setContent(request.getContent());
            report.setUpdatedAt(LocalDateTime.now());
            reportRepository.save(report);
        }

        public void addAvailableTimes(List<String> times) {
            Mentor mentor = getLoggedInMentor(); // 현재 로그인된 멘토를 가져오는 메서드
            mentor.setAvailableTimes(times);
            mentorRepository.save(mentor);
        }

        private Mentor getLoggedInMentor() {
            String username = SecurityContextHolder.getContext().getAuthentication().getEmail();

            return mentorRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
}

