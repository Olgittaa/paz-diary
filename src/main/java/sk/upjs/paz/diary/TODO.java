package sk.upjs.paz.diary;

public final class TODO {

	// ==================
	// Bugs =
	// ==================

	/*
	 * 1. При добавлении/удалении энтиты, рефрешая мы подгружаем заново целый
	 * список, производительнее бы было чтобы мы меняли только то над чем проходили
	 * изменения
	 * 
	 * 2. 2раза кликаешь на дз оно меняет состояние выполненности 2р -> 2 лишних
	 * запроса к ДБ(Исправил тем что добавил условия на открытие описания с помощью
	 * контрол+ЛКМ)
	 * 
	 * 3. Если открыть и закрыть окно эдитации ноумворка запросы обновления все
	 * равно пройдут не зависимо от того изменил что-то или нет(Исправил булиан
	 * значением wereChanges)
	 * 
	 * 4. Модифицировать метод loadWindow так чтобы мог устанавливать и минимальные
	 * значения окна
	 */

	// ==================
	// Todo =
	// ==================

	/*
	 * 1. Поискать баги в хм. Напр: поиск добавление дубликатов и тд....
	 * 
	 * 2. Как быстрее сортить через колекшнс или мускл
	 *
	 *
	 */

	private TODO() {
	}

	// ResultSetExtructor - nacitava celu tabulky a vracia list cohosi
	// RawMapper - nacitava po jednemu riadku s tabulky a vracia jeden riadok

	//

}
