package org.firstinspires.ftc.teamcode.robot.opmode.teleop.subsystem;


import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.config.Hardware;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Outtake {
    private Hardware hardware;
    private Telemetry telemetry;
    private Gamepad gamepad2;
    private boolean feederToggle = false;
    private double currentVoltage;
    private double pastPos;

    public Outtake(Hardware hardware, Telemetry telemetry, Gamepad gamepad2) {
        this.hardware = hardware;
        this.telemetry = telemetry;
        this.gamepad2 = gamepad2;

    }


    public ElapsedTime run(ElapsedTime readyTime) {//altered with time
        //outtakeR is top
        currentVoltage = hardware.myControlHubVoltageSensor.getVoltage();
        int currentPos = hardware.outtakeL.getCurrentPosition();
        telemetry.addData("encoder", currentPos);
        telemetry.update();
        if (gamepad2.x) {
            hardware.outtakeL.setPower(1);
            hardware.outtakeR.setPower(0.8);
            //hardware.outtakeR.setPower(1);
        }
        else if (gamepad2.y) {
            hardware.outtakeL.setPower(0.8);
            hardware.outtakeR.setPower(1);
        }
        else {
            readyTime.reset();
            hardware.outtakeL.setPower(0);
            hardware.outtakeR.setPower(0);
        }

        //for outtakeFeeder

        //go backward (hopefully unneeded
        /*
        if ((currentGamepad2.left_trigger>0.8) && (previousGamepad2.left_trigger<0.8)) {
            feederToggle = !feederToggle;
        }

        if (feederToggle) {
            hardware.intake.setPower(1);
        } else {
            hardware.intake.setPower(0);
        }*/

        if ((gamepad2.right_trigger>0.8 && hardware.outtakeL.getPower()<0.2) || (gamepad2.right_trigger>0.8 && hardware.outtakeL.getPower()>0.2 && readyTime.seconds()>1.5)) {//altered with time
            hardware.feedOuttake(-1);//inv for controller's sake
        }
        else if (gamepad2.left_trigger>0.8) {
            hardware.feedOuttake(1);
        }
        else {
            hardware.feedOuttake(0);
        }
        return readyTime;//altered with time
    }
}
