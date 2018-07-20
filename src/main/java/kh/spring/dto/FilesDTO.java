package kh.spring.dto;

public class FilesDTO {
	private int seq;
	private int article_no;
	private String original_file_name;
	private String save_file_name;
	
	public FilesDTO() {}
	

	public FilesDTO(int seq, int article_no, String original_file_name, String save_file_name) {
		super();
		this.seq = seq;
		this.article_no = article_no;
		this.original_file_name = original_file_name;
		this.save_file_name = save_file_name;
	}


	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getOriginal_file_name() {
		return original_file_name;
	}

	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}

	public String getSave_file_name() {
		return save_file_name;
	}

	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	
	
	
}
