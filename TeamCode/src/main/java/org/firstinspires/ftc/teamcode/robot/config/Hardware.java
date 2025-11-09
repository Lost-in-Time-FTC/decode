package org.firstinspires.ftc.teamcode.robot.config;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;

public class Hardware {
    public DcMotor fL;
    public DcMotor fR;
    public DcMotor bL;
    public DcMotor bR;
    public List<DcMotor> driveMotors;
    public DcMotor outtakeL;
    public DcMotor outtakeR;
    public List<DcMotor> outtakeMotors;
    public Servo outtakeFeeder;
    public DcMotor intake;

    public Hardware(HardwareMap hardwareMap) {
        fR = hardwareMap.get(DcMotor.class, "fR");
        fL = hardwareMap.get(DcMotor.class, "fL");
        bR = hardwareMap.get(DcMotor.class, "bR");
        bL = hardwareMap.get(DcMotor.class, "bL");
        driveMotors = Arrays.asList(fR, fL, bR, bL);
        outtakeL = hardwareMap.get(DcMotor.class, "outtakeL");
        outtakeR = hardwareMap.get(DcMotor.class, "outtakeR");
        outtakeMotors = Arrays.asList(outtakeL, outtakeR);
        intake = hardwareMap.get(DcMotor.class, "intake");
        outtakeFeeder = hardwareMap.get(Servo.class, "outtakeFeeder");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        bL.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.FORWARD);
        fL.setDirection(DcMotor.Direction.REVERSE);
        fR.setDirection(DcMotor.Direction.FORWARD);

        outtakeL.setDirection(DcMotor.Direction.REVERSE);
        outtakeR.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
    }

    public void moveForward(double power) {
        bL.setPower(power);
        bR.setPower(power);
        fL.setPower(power);
        fR.setPower(power);
    }

    public void moveBackward(double power) {
        bL.setPower(-power);
        bR.setPower(-power);
        fL.setPower(-power);
        fR.setPower(-power);
    }

    public void strafeLeft(double power) {
        bL.setPower(-power);
        bR.setPower(power);
        fL.setPower(-power);
        fR.setPower(power);
    }

    public void strafeRight(double power) {
        bL.setPower(power);
        bR.setPower(-power);
        fL.setPower(power);
        fR.setPower(-power);
    }

    public final void rotateLeft(double power) {
        bL.setPower(power);
        bR.setPower(power);
        fL.setPower(-power);
        fR.setPower(-power);
    }

    public final void rotateRight(double power) {
        fR.setPower(-power);
        bR.setPower(-power);
        fL.setPower(power);
        bL.setPower(power);
    }

    public final void stopDrive() {
        fR.setPower(0);
        bR.setPower(0);
        fL.setPower(0);
        bL.setPower(0);
    }
}
