package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedConstruction.Context;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;

public class FooServiceTest {

    @Test
    public void testFooConstructor() {
        assertThat(new Foo().stringReturningInstanceMethod())
                .isEqualTo("originalRealValue");
        FooService fooService = new FooService();
        try (MockedConstruction<Foo> mocked = mockConstruction(Foo.class, this::prepareFoo)) {
            String result = fooService.methodThatCallsNewFooInstanceMethod();
            assertThat(result)
                    .isEqualTo("bar");

            // (optional) verify mock Foo was called by myFooService
            Foo fooMock = mocked.constructed().get(0);
            verify(fooMock).stringReturningInstanceMethod();
        }
        assertThat(new Foo().stringReturningInstanceMethod())
                .isEqualTo("originalRealValue");
    }

    private void prepareFoo(Foo mock, Context context) throws Throwable {
        doReturn("bar").when(mock).stringReturningInstanceMethod();
    }
}
