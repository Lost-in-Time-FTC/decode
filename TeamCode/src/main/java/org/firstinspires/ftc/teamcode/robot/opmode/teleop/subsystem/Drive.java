package org.firstinspires.ftc.teamcode.robot.opmode.teleop.subsystem;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.config.Hardware;

public class Drive {
    private Hardware hardware;
    private Telemetry telemetry;
    private Gamepad gamepad1;

    public Drive(Hardware hardware, Telemetry telemetry, Gamepad gamepad1) {
        this.hardware = hardware;
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
    }

    public void run() {//Gamepad previousGamepad1, Gamepad currentGamepad1) {
        // Mecanum
        double orientation = 1;
        double drive = gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x; //added neg
        double strafe = gamepad1.left_stick_x; //added neg now removed

        // Strafing

        double fL = Range.clip(drive + strafe + turn, -0.5, 0.5);
        double fR = Range.clip(drive - strafe - turn, -0.5, 0.5); //strafe had plus
        double bL = Range.clip(drive - strafe + turn, -0.5, 0.5);
        double bR = Range.clip(drive + strafe - turn, -0.5, 0.5); //strafe had minus

        double rapidMode = 1.75;
        double sniperMode = 0.5;

            /*try {
                previousGamepad1.copy(currentGamepad1);
                currentGamepad1.copy(gamepad1);
            } catch (Exception e) {}

            if (currentGamepad1.x && !previousGamepad1.x) {
                orientation = orientation * -1;
                //change robot orientation

            }*/
        if (gamepad1.x) {
            orientation = -1;
        }
        else {
            orientation = 1;
        }

        // Sniper mode
        if (gamepad1.left_trigger > 0) {
            hardware.fL.setPower(fL * rapidMode * sniperMode*orientation);
            hardware.fR.setPower(fR * rapidMode * sniperMode*orientation);
            hardware.bL.setPower(bL * rapidMode * sniperMode*orientation);
            hardware.bR.setPower(bR * rapidMode * sniperMode*orientation);
        }
        // Brakes
        else if (gamepad1.right_trigger > 0) {
            hardware.fL.setPower(fL * 0);
            hardware.fR.setPower(fR * 0);
            hardware.bL.setPower(bL * 0);
            hardware.bR.setPower(bR * 0);

        }
        // Normal drive
        else {
            hardware.fL.setPower(fL * rapidMode*orientation);
            hardware.fR.setPower(fR * rapidMode*orientation);
            hardware.bL.setPower(bL * rapidMode*orientation);
            hardware.bR.setPower(bR * rapidMode*orientation);
        }
    }
}
