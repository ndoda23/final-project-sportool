package service;

import dao.TrainerDao;
import model.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrainerServiceTest {

    private TrainerService trainerService;
    private TrainerDao mockTrainerDao;

    @BeforeEach
    void setUp() {
        System.setProperty("net.bytebuddy.experimental", "true");
        mockTrainerDao = Mockito.mock(TrainerDao.class);
        trainerService = new TrainerService(mockTrainerDao);
    }

    @Test
    void testGetAllTrainers() {
        Trainer t1 = new Trainer(1, 1, "Dato", "Okmelashvili",
                "599123456", "Football", 50.0, 4.5, 10);
        Trainer t2 = new Trainer(2, 2, "Nika", "Dodashvili",
                "577123456", "Basketball", 40.0, 3.8, 5);

        when(mockTrainerDao.getAllTrainers()).thenReturn(Arrays.asList(t1, t2));

        List<Trainer> trainers = trainerService.getAllTrainers();

        assertNotNull(trainers);
        assertEquals(2, trainers.size());
        assertEquals("Dato", trainers.get(0).getFirstName());
        assertEquals("Nika", trainers.get(1).getFirstName());
    }

    @Test
    void testGetTrainerById() {
        Trainer trainer = new Trainer(1, 1, "Dato", "Okmelashvili",
                "599123456", "Football", 50.0, 4.5, 10);

        when(mockTrainerDao.getTrainerById(1)).thenReturn(trainer);

        Trainer result = trainerService.getTrainerById(1);

        assertNotNull(result);
        assertEquals("Dato", result.getFirstName());
    }

    @Test
    void testGetTrainerByIdNotFound() {
        when(mockTrainerDao.getTrainerById(999)).thenReturn(null);

        Trainer result = trainerService.getTrainerById(999);

        assertNull(result);
    }

    @Test
    void testGetTrainerByIdInvalidId() {
        Trainer result = trainerService.getTrainerById(-1);

        assertNull(result);
        verify(mockTrainerDao, never()).getTrainerById(anyInt());
    }

    @Test
    void testAddTrainerSuccess() {
        Trainer trainer = new Trainer(0, 1, "Dato", "Okmelashvili",
                "599123456", "Football", 50.0, 0.0, 0);

        when(mockTrainerDao.addTrainer(trainer)).thenReturn(true);

        assertTrue(trainerService.addTrainer(trainer));
    }

    @Test
    void testAddTrainerEmptyFirstName() {
        Trainer trainer = new Trainer(0, 1, "", "Okmelashvili",
                "599123456", "Football", 50.0, 0.0, 0);

        assertFalse(trainerService.addTrainer(trainer));
        verify(mockTrainerDao, never()).addTrainer(any());
    }

    @Test
    void testAddTrainerEmptyLastName() {
        Trainer trainer = new Trainer(0, 1, "Dato", "",
                "599123456", "Football", 50.0, 0.0, 0);

        assertFalse(trainerService.addTrainer(trainer));
        verify(mockTrainerDao, never()).addTrainer(any());
    }

    @Test
    void testAddTrainerEmptyPhone() {
        Trainer trainer = new Trainer(0, 1, "Dato", "Okmelashvili",
                "", "Football", 50.0, 0.0, 0);

        assertFalse(trainerService.addTrainer(trainer));
        verify(mockTrainerDao, never()).addTrainer(any());
    }

    @Test
    void testAddTrainerEmptySportType() {
        Trainer trainer = new Trainer(0, 1, "Dato", "Okmelashvili",
                "599123456", "", 50.0, 0.0, 0);

        assertFalse(trainerService.addTrainer(trainer));
        verify(mockTrainerDao, never()).addTrainer(any());
    }

    @Test
    void testAddTrainerInvalidPrice() {
        Trainer trainer = new Trainer(0, 1, "Dato", "Okmelashvili",
                "599123456", "Football", 0.0, 0.0, 0);

        assertFalse(trainerService.addTrainer(trainer));
        verify(mockTrainerDao, never()).addTrainer(any());
    }
    @Test
    void testDefaultConstructor() {
        TrainerService service = new TrainerService();
        assertNotNull(service);
    }
}