package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedConstruction.Context;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;

public class DiceRollerTest {

    @Test
    public void testDiceRoll() {
        try (MockedConstruction<Random> mocked = mockConstruction(Random.class, this::prepareRandom)) {
            DiceRoller dicerRoller = new DiceRoller();
            assertThat(dicerRoller.roll()).isEqualTo(6);

            Random mockRandom = mocked.constructed().get(0);
            verify(mockRandom).nextInt(6);
        }
    }

    // implements MockInitializer
    private void prepareRandom(Random mockRandom, Context context) {
        doReturn(5).when(mockRandom).nextInt(6);
    }
}
