package kh.spring.dto;

public class BoardDTO {
	private int seq;
	private String title;
	private String contents;
	private String writer;
	private String writedate;
	private int viewcount;
	private String ip;
	
	public BoardDTO() {
		super();
	}
	
	public BoardDTO(int seq, String title, String contents, String writer, String writedate, int viewcount, String ip) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.writedate = writedate;
		this.viewcount = viewcount;
		this.ip = ip;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
