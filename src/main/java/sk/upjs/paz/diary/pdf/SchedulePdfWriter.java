package sk.upjs.paz.diary.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.util.ArrayList;
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
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.ISubjectDAO;

/**
 * Class writes 5 tables to pdf file, representing day of week, with a daily
 * schedule
 * 
 * @author Yevhenii Kozhevin
 * @author Olga Charna
 */
public class SchedulePdfWriter {
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
	private static final ISubjectDAO subjectDao = DaoFactory.getSubjectDao();

	public SchedulePdfWriter(String fileName) {
		LOGGER = LoggerFactory.getLogger(SchedulePdfWriter.class);
		DOCUMENT = initDocument(fileName);
		LESSONS = DaoFactory.getLessonDao().getWeekSchedule();
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
	 * Creates schedule to pdf file
	 */
	public void createPdf() {
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
					new Chunk(text, FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)));
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
			table.setTotalWidth(new float[] { 200 });
			changeTableHeader(table, day);
			// TODO увеличить шрифт таблицы, саму таблицу, по высоте и ширине, разделить
			// таблицы
			fillTable(day).forEach(l -> { // много мусора
				String rowFullFill = subjectDao.getNameById(l.getIdSubject()) + " " + l.getStartTime() + " - "
						+ l.getEndTime();
				table.addCell(new PdfPCell(new Phrase(rowFullFill)));
			});

			DOCUMENT.add(table);
		} catch (DocumentException e) {
			LOGGER.error("An error has occurred in a Document", e);
		}
	}

	/**
	 * Adds rows to table
	 * 
	 * @param table - table to add rows
	 */
	private List<Lesson> fillTable(String day) {
		switch (day) {
		case "Monday":
			return LESSONS.stream().filter(l -> l.getDate().getDayOfWeek() == DayOfWeek.MONDAY)
					.collect(Collectors.toList());
		case "Tuesday":
			return LESSONS.stream().filter(l -> l.getDate().getDayOfWeek() == DayOfWeek.TUESDAY)
					.collect(Collectors.toList());

		case "Wednesday":
			return LESSONS.stream().filter(l -> l.getDate().getDayOfWeek() == DayOfWeek.WEDNESDAY)
					.collect(Collectors.toList());

		case "Thursday":
			return LESSONS.stream().filter(l -> l.getDate().getDayOfWeek() == DayOfWeek.THURSDAY)
					.collect(Collectors.toList());

		case "Friday":
			return LESSONS.stream().filter(l -> l.getDate().getDayOfWeek() == DayOfWeek.FRIDAY)
					.collect(Collectors.toList());
		}
		// header.setHorizontalAlignment(Element.ALIGN_CENTER);
		return null;
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
