package com.syntex.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class PcParts {

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CpuPart extends Part {
        private int core_count;
        private double core_clock;
        private double boost_clock;
        private int tdp;
        private String graphics;
        private boolean smt;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CpuCoolerPart extends Part {
        private Object rpm; // Can be a single value or range [min, max]
        private Object noise_level; // Can be a single value or range [min, max]
        private String color;
        private Integer size; // Using Integer to allow null values
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class MotherboardPart extends Part {
        private String socket;
        private String form_factor;
        private int max_memory;
        private int memory_slots;
        private String color;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class MemoryPart extends Part {
        private int[] speed; // [DDR version, RAM speed]
        private int[] modules; // [number of modules, size of each module]
        private double price_per_gb;
        private String color;
        private double first_word_latency;
        private int cas_latency;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class InternalHardDrivePart extends Part {
        private int capacity;
        private double price_per_gb;
        private String type;
        private Integer cache; // Using Integer to allow null
        private String form_factor;
        private String interface_; // Underscore due to Java keyword
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class VideoCardPart extends Part {
        private String chipset;
        private int memory;
        private int core_clock;
        private int boost_clock;
        private String color;
        private int length;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CasePart extends Part {
        private String type;
        private String color;
        private Integer psu; // Optional, wattage of included PSU
        private String side_panel;
        private double external_volume;
        private int internal_35_bays;
        private String form_factor;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class PowerSupplyPart extends Part {
        private String type;
        private String efficiency;
        private int wattage;
        private String modular; // String ("Full", "Semi")
        private String color;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class MonitorPart extends Part {
        private double screen_size;
        private int[] resolution; // [width, height]
        private int refresh_rate;
        private double response_time;
        private String panel_type;
        private String aspect_ratio;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SoundCardPart extends Part {
        private double channels;
        private int digital_audio;
        private int snr;
        private int sample_rate;
        private String chipset;
        private String interface_;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WiredNetworkCardPart extends Part {
        private String interface_;
        private String color;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WirelessNetworkCardPart extends Part {
        private String protocol;
        private String interface_;
        private String color;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class HeadphonesPart extends Part {
        private String type;
        private int[] frequency_response; // [min, max]
        private boolean microphone;
        private boolean wireless;
        private String enclosure_type;
        private String color;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class KeyboardPart extends Part {
        private String style;
        private String switches;
        private String backlit;
        private boolean tenkeyless;
        private String connection_type;
        private String color;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class MousePart extends Part {
        private String tracking_method;
        private String connection_type;
        private int max_dpi;
        private String hand_orientation;
        private String color;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SpeakersPart extends Part {
        private int configuration;
        private double wattage;
        private int[] frequency_response; // [lower, upper]
        private String color;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WebcamPart extends Part {
        private String[] resolutions;
        private String connection;
        private String focus_type;
        private String[] os;
        private double fov;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CaseAccessoryPart extends Part {
        private String type;
        private Object form_factor; // Number or null
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CaseFanPart extends Part {
        private int size;
        private String color;
        private Object rpm; // Number or [min, max] or null
        private Object airflow; // Number or [min, max] or null
        private Object noise_level; // Number or [min, max] or null
        private boolean pwm;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class FanControllerPart extends Part {
        private Integer channels;
        private Integer channel_wattage; // Integer to allow null
        private Boolean pwm; // Boolean to allow null
        private Object form_factor; // Can be null
        private String color;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ThermalPastePart extends Part {
        private double amount;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ExternalHardDrivePart extends Part {
        private String type;
        private String interface_;
        private int capacity;
        private double price_per_gb;
        private String color;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class OpticalDrivePart extends Part {
        private Integer bd; // Integer to allow null
        private Integer dvd;
        private Integer cd;
        private String bd_write;
        private String dvd_write;
        private String cd_write;
        private String type;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UpsPart extends Part {
        private int capacity_w;
        private int capacity_va;
        private String type;
    }
}