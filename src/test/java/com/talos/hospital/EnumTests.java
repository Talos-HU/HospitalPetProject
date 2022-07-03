package com.talos.hospital;

import com.talos.hospital.Model.Enum.Gender;
import com.talos.hospital.Model.Enum.Position;
import com.talos.hospital.Util.GenderConverter;
import com.talos.hospital.Util.PositionConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EnumTests {

    @Mock
    private GenderConverter genderConverter;

    @Mock
    private PositionConverter positionConverter;

    @Test
    void genderConverterShouldWorkAsIntended() {
        String gender = String.valueOf(when(genderConverter.convertToDatabaseColumn(Gender.MALE)).thenReturn(Gender.MALE.name()));
        assertThat(gender.equals("Male"));
    }

    @Test
    void positionConverterShouldWorkAsIntended() {
        String position = String.valueOf(when(positionConverter.convertToDatabaseColumn(Position.FELLOW)).thenReturn(Position.FELLOW.getPosition()));
        assertThat(position.equals("Fellow"));
    }
}
