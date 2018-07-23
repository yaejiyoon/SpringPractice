package kh.spring.dto;

public class CommentDTO {
	private int article_no;
	private int comment_seq;
	private String comment_text;
	private String writer;
	private String writeDate;
	private String ip;
	
	public CommentDTO() {
		super();
	}

	public CommentDTO(int article_no, int comment_seq, String comment_text, String writer, String writeDate,
			String ip) {
		super();
		this.article_no = article_no;
		this.comment_seq = comment_seq;
		this.comment_text = comment_text;
		this.writer = writer;
		this.writeDate = writeDate;
		this.ip = ip;
	}



	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public int getComment_seq() {
		return comment_seq;
	}

	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	
}
