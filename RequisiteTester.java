import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequisiteTester {
    private Requisite requisitePre;
    private Requisite requisiteCo;
    private Requisite requisitePreOrCo;

    @BeforeEach
    public void setup() {
        requisitePre = new Requisite(RequisiteType.pre, "CS145", Grade.B);
        requisiteCo = new Requisite(RequisiteType.co, "MATH141", Grade.C);
        requisitePreOrCo = new Requisite(RequisiteType.pre_or_co, "ENG101", Grade.A);
    }

    @AfterEach
    public void tearDown() {
        requisitePre = null;
        requisiteCo = null;
        requisitePreOrCo = null;
    }

    @Test
    public void testRequisitePreType() {
        assertEquals(RequisiteType.pre, requisitePre.getType(), "The type should be pre.");
    }

	@Test
    public void testRequisitePreOrCoType() {
        assertEquals(RequisiteType.pre_or_co, requisitePreOrCo.getType(), "The type be pre_or_co.");
    }

    @Test
    public void testRequisiteCoType() {
        assertEquals(RequisiteType.co, requisiteCo.getType(), "The type should be co.");
    }

    @Test
    public void testRequisitePreToStringFormat() {
        String expectedString = "pre: CS145 (B)";
        assertEquals(expectedString, requisitePre.toString(), "The toString method should return the correct string");
    }

    @Test
    public void testRequisiteCoToStringFormat() {
        String expectedString = "co: MATH141 (C)";
        assertEquals(expectedString, requisiteCo.toString(), "The toString method should return the correct string");
    }

    @Test
    public void testRequisitePreOrCoToStringFormat() {
        String expectedString = "pre_or_co: ENG101 (A)";
        assertEquals(expectedString, requisitePreOrCo.toString(), "The toString method should return the correct string");
    }
}