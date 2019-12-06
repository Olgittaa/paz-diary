package sk.upjs.paz.diary.gui;

import sk.upjs.paz.diary.entity.Lesson;

public class LessonEditController extends Controller {

	private Lesson selectedLesson;

	public LessonEditController() {
	}

	public LessonEditController(Lesson lesson) {
		this.selectedLesson = lesson;
	}

}
