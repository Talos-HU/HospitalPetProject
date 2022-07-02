package data;

import com.talos.hospital.Model.Entity.Supply;
import com.talos.hospital.Model.Enum.Pretence;

import java.util.UUID;

public interface TestSupplies {

    Supply ASPIRIN = new Supply(
            UUID.fromString("cd49510e-e1f2-4ea2-8b7c-2dd9dd407a4b"),
            "Aspirin",
            Pretence.TA,
            15,
            1500,
            1500
    );

    Supply INVALIDMEDICINE = new Supply(
            UUID.fromString("098bd847-b16c-4cd3-bb06-d589718521fd"),
            "!!!!!!",
            Pretence.TA,
            15,
            1500,
            1500
    );

    Supply ALGOFLEX = new Supply(
            UUID.fromString("674c80e3-d57a-4cfe-be92-0b9a06f8e7ce"),
            "Algoflex",
            Pretence.COVERED,
            25,
            1750,
            0
    );
    Supply Supradyn = new Supply(
            UUID.fromString("e48d7110-ee8c-44ac-bbfe-f09a1db603db"),
            "Supradyn",
            Pretence.EK,
            22,
            4500,
            3250
    );

    Supply Insuline = new Supply(
            UUID.fromString("6be0e628-e560-483a-8b96-26823cd0e650"),
            "Insuline",
            Pretence.GM,
            250,
            11500,
            7600
    );
}
