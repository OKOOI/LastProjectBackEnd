package project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.dto.NoticeDto;
import project.dto.QnaCommentDto;
import project.dto.QnaDto;

@Mapper
public interface ProjectMapper {

	// 공지사항 리스트 조회
	public List<NoticeDto> listNoticeDto() throws Exception;

	// 공지사항 글별 상세페이지 조회
	public NoticeDto noticeDetail(int noticeIdx) throws Exception;

	// 공지사항 작성
	public int insertNotice(NoticeDto noticeDto) throws Exception;

	// 공지사항 삭제
	public int deleteNotice(int noticeIdx) throws Exception;

	// 공지사항 수정
	public int updateNotice(NoticeDto noticeDto) throws Exception;

	// QNA 리스트 조회
	public List<QnaDto> listQnaDto() throws Exception;

	// QNA 상세페이지 조회
//	public QnaDto qnaDetail(int qnaIdx) throws Exception;

	// QNA 작성
	public int insertQna(QnaDto qnaDto) throws Exception;

	// QNA 삭제
	public int deleteQna(int qnaIdx) throws Exception;

	// QNA 수정
	public int updateQna(QnaDto qnaDto) throws Exception;
	
	// QNA 페이지별 조회
	public List<QnaDto> listQnaDtoByPages(int pages) throws Exception;
	
	// QNA 페이지수 조회
	public int listQnaDtoPageCount() throws Exception;

	// QNA 상세페이지 COMMENT 리스트
	public List<QnaCommentDto> selectCommentList(int qnaIdx) throws Exception;
	
	// QNA 상세페이지 조회
	public QnaDto selectQnaInfo(int qnaIdx) throws Exception;

	// QNA 상세페이지 COMMENT 등록
	public int insertComment(QnaCommentDto qnaCommentDto) throws Exception;

}
