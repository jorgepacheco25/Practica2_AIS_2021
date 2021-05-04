package es.urjc.code.daw.library.book;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.urjc.code.daw.library.notification.NotificationService;

import static org.mockito.Mockito.*;


class BookUnityTest {
	
	private BookRepository repo;
	private NotificationService notifications;
	private BookService service;
	
	@BeforeEach
	void setup() {
		this.repo = mock(BookRepository.class);
		this.notifications = mock(NotificationService.class);
		this.service = new BookService(repo, notifications);
	}
	
	@Test
	void givenValidBook_whenIsSaved_thenIsSavedRepoAndNotify(){
		
		//Given
		Book book = mock(Book.class);
		when(repo.save(book)).thenReturn(book);
		when(book.getTitle()).thenReturn("Virtual Hero");
		when(book.getDescription()).thenReturn("Rubius");
		
		//When
		this.service.save(book);
		
		//Then
		verify(repo).save(book);
		verify(notifications).notify("Book Event: book with title="+book.getTitle()+" was created");

	}
	
	
	@Test
	void givenValidBook_whenIsDelete_thenIsDeleteRepoAndNotify(){
		
		//Given
		Book book2 = mock(Book.class);
		when(book2.getId()).thenReturn((long) 02);
		when(book2.getDescription()).thenReturn("Description");
		when(repo.save(book2)).thenReturn(book2);
		this.service.save(book2);
		
		//When
		this.service.delete(book2.getId());
		
		//Then
		verify(repo).deleteById(book2.getId());
		verify(notifications).notify("Book Event: book with id="+book2.getId()+" was deleted");
		
	}

}
