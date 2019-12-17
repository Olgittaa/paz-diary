package sk.upjs.paz.diary.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.persistence.DaoFactory;

/**
 * Class writes 5 tables to pdf file, representing day of week, with a daily
 * schedule
 * 
 * @author Yevhenii Kozhevin
 * @author Olga Charna
 */
public class SchedulePdfWriter implements sk.upjs.paz.diary.pdf.PdfWriter{
	/** Logger */
	private final Logger LOGGER;

	/**
	 * Document, where can be added chunks and tables
	 */
	private final Document DOCUMENT;

	/**
	 * Lessons for current week
	 */
	private final List<Lesson> LESSONS;

	public SchedulePdfWriter(String fileName) {
		LOGGER = LoggerFactory.getLogger(SchedulePdfWriter.class);
		DOCUMENT = initDocument(fileName);
		LESSONS = DaoFactory.INSTANCE.getLessonDao().getWeekSchedule();
	}

	/**
	 * Initializes document
	 * 
	 * @param fileName - file
	 * @return initialized document
	 */
	private Document initDocument(String fileName) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			LOGGER.error("An error has occurred while opening the file + \"" + fileName + "\". "
					+ "It is either open or does not exist", e);
		} catch (DocumentException e) {
			LOGGER.error("An error has occurred in a Document", e);
		}
		return document;
	}

	/**
	 * Writes schedule to pdf file
	 */
	@Override
	public void writeToPdf() {
		DOCUMENT.open();
		writeSchedule();
		DOCUMENT.close();
	}

	/**
	 * Writes schedule to pdf file
	 */
	private void writeSchedule() {
		writeChunk("Schedule");
		final String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		for (int i = 0; i < 5; i++) {
			writeTable(days[i]);
		}
	}

	/**
	 * Writes one line of text to pdf File
	 * 
	 * @param text - text to write
	 */
	private void writeChunk(String text) {
		try {
			Paragraph paragraph = new Paragraph(
					new Chunk(text, FontFactory.getFont(FontFactory.HELVETICA, 36, BaseColor.BLACK)));
			paragraph.setSpacingAfter(20f);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			DOCUMENT.add(paragraph);
		} catch (DocumentException e) {
			LOGGER.error("An error has occurred in a Document", e);
		}
	}

	/**
	 * Writes table to pdf file
	 * 
	 * @param day - day of week, which is a header of a table
	 */
	private void writeTable(String day) {
		try {
			PdfPTable table = new PdfPTable(1);
			table.setLockedWidth(true);
			table.setTotalWidth(new float[] { 250 });
			table.setSpacingAfter(10f);

			changeTableHeader(table, day);

			fillTable(day, table);
			table.setHorizontalAlignment(getHorizontalAlignment(day));

			DOCUMENT.add(table);
		} catch (DocumentException e) {
			LOGGER.error("An error has occurred in a Document", e);
		}
	}

	/**
	 * Returns horizontal alignment for current day
	 *
	 * @param day - day of week, which identifies a table
	 * @return horizontal alignment for current day
	 */
	private int getHorizontalAlignment(String day) {
		switch (day) {
		case "Monday":
		case "Wednesday":
		case "Friday":
			return Element.ALIGN_LEFT;
		case "Tuesday":
		case "Thursday":
			return Element.ALIGN_RIGHT;
		}
		return -1;
	}

	/**
	 * Fills table with lessons
	 * 
	 * @param day   - lessons of which day to fill
	 * @param table - table to fill
	 */
	private void fillTable(String day, PdfPTable table) {
		listLessonsByDay(day).forEach(l -> {
			StringBuilder rowFullFill = new StringBuilder(l.toString());
			PdfPCell cell = new PdfPCell(new Phrase(rowFullFill.toString()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
		});
	}

	/**
	 * Returns list of lessons in a current day
	 * 
	 * @param table - table to add rows
	 * @return list of lesson in a current day
	 */
	private List<Lesson> listLessonsByDay(String day) {
		return LESSONS.stream().filter(l -> l.getDateTime().getDayOfWeek() == DayOfWeek.valueOf(day.toUpperCase()))
				.collect(Collectors.toList());
	}

	/**
	 * Changes columns title
	 * 
	 * @param table      - table to change columns name
	 * @param columnName - name we want to see as column's name
	 */
	private void changeTableHeader(PdfPTable table, String columnName) {
		PdfPCell header = new PdfPCell();
		header.setBackgroundColor(new BaseColor(204, 153, 255));
		header.setBorderWidth(1);
		header.setPhrase(new Phrase(columnName));
		header.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(header);
	}

}