package com.interview.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="style")
public class Style {
	
	@Id
	@GeneratedValue
	@Column(name="style_id")
	private Integer style_id;
	
	@Column(name="bkg_clr")
	private String background_color;
	
	@Column(name="font_size")
	private String font_size;
	
	@Column(name="font_clr")
	private String font_color;

	public Integer getStyle_id() {
		return style_id;
	}

	public void setStyle_id(Integer style_id) {
		this.style_id = style_id;
	}

	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	public String getFont_size() {
		return font_size;
	}

	public void setFont_size(String font_size) {
		this.font_size = font_size;
	}

	public String getFont_color() {
		return font_color;
	}

	public void setFont_color(String font_color) {
		this.font_color = font_color;
	}
	
}
