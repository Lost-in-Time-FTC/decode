package org.firstinspires.ftc.teamcode.robot.opmode.teleop.subsystem;


import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.config.Hardware;

public class Outtake {
    private Hardware hardware;
    private Telemetry telemetry;
    private Gamepad gamepad2;
    private boolean pincerToggle = false;
    private double PINCER_OPEN = 0;
    private double PINCER_CLOSE = 1;
    private boolean pincerTwistToggle = false;
    private double PINCER_TWIST_90 = 0.5;
    private double PINCER_TWIST_0 = 0;

    public Outtake(Hardware hardware, Telemetry telemetry, Gamepad gamepad2) {
        this.hardware = hardware;
        this.telemetry = telemetry;
        this.gamepad2 = gamepad2;
    }

    public void run() {
        if (gamepad2.x) {
            hardware.outtakeL.setPower(0.75);
            hardware.outtakeR.setPower(0.75);
        }
    }
}
