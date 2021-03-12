package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class FooTest {

    @Test
    public void testInstanceMethods() {
        Foo fooInstance = mock(Foo.class);
        assertThat(fooInstance.stringReturningInstanceMethod())
                .isNull();
        assertThat(fooInstance.intReturningInstanceMethod())
                .isEqualTo(0);
    }

    @Test
    public void testStaticMethods() {
        assertThat(Foo.stringReturningStaticMethod())
                .isEqualTo("originalRealValue");

        // mock static methods on Foo.class
        try (MockedStatic<Foo> mocked = mockStatic(Foo.class)) {
            mocked.when(Foo::stringReturningStaticMethod)
                    .thenReturn("fakeMockValue");

            // Foo.staticMethod() behavior is changed only within this try scope
            assertThat(Foo.stringReturningStaticMethod()).isEqualTo("fakeMockValue");

            // (optional) verify that Foo.staticMethod() was called
            mocked.verify(Foo::stringReturningStaticMethod);
        }

        // staticMethod() behavior reverts to original unmocked behavior after exiting try scope
        assertThat(Foo.stringReturningStaticMethod())
                .isEqualTo("originalRealValue");
    }

}
