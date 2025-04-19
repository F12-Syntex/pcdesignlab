package com.syntex.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.syntex.types.PcParts.CaseAccessoryPart;
import com.syntex.types.PcParts.CaseFanPart;
import com.syntex.types.PcParts.CasePart;
import com.syntex.types.PcParts.CpuCoolerPart;
import com.syntex.types.PcParts.CpuPart;
import com.syntex.types.PcParts.ExternalHardDrivePart;
import com.syntex.types.PcParts.FanControllerPart;
import com.syntex.types.PcParts.HeadphonesPart;
import com.syntex.types.PcParts.InternalHardDrivePart;
import com.syntex.types.PcParts.KeyboardPart;
import com.syntex.types.PcParts.MemoryPart;
import com.syntex.types.PcParts.MonitorPart;
import com.syntex.types.PcParts.MotherboardPart;
import com.syntex.types.PcParts.MousePart;
import com.syntex.types.PcParts.OpticalDrivePart;
import com.syntex.types.PcParts.OsPart;
import com.syntex.types.PcParts.PowerSupplyPart;
import com.syntex.types.PcParts.SoundCardPart;
import com.syntex.types.PcParts.SpeakersPart;
import com.syntex.types.PcParts.ThermalPastePart;
import com.syntex.types.PcParts.UpsPart;
import com.syntex.types.PcParts.VideoCardPart;
import com.syntex.types.PcParts.WebcamPart;
import com.syntex.types.PcParts.WiredNetworkCardPart;
import com.syntex.types.PcParts.WirelessNetworkCardPart;

import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CpuPart.class, name = "cpu"),
    @JsonSubTypes.Type(value = CpuCoolerPart.class, name = "cpu-cooler"),
    @JsonSubTypes.Type(value = MotherboardPart.class, name = "motherboard"),
    @JsonSubTypes.Type(value = MemoryPart.class, name = "memory"),
    @JsonSubTypes.Type(value = InternalHardDrivePart.class, name = "internal-hard-drive"),
    @JsonSubTypes.Type(value = VideoCardPart.class, name = "video-card"),
    @JsonSubTypes.Type(value = CasePart.class, name = "case"),
    @JsonSubTypes.Type(value = PowerSupplyPart.class, name = "power-supply"),
    @JsonSubTypes.Type(value = OsPart.class, name = "os"),
    @JsonSubTypes.Type(value = MonitorPart.class, name = "monitor"),
    @JsonSubTypes.Type(value = SoundCardPart.class, name = "sound-card"),
    @JsonSubTypes.Type(value = WiredNetworkCardPart.class, name = "wired-network-card"),
    @JsonSubTypes.Type(value = WirelessNetworkCardPart.class, name = "wireless-network-card"),
    @JsonSubTypes.Type(value = HeadphonesPart.class, name = "headphones"),
    @JsonSubTypes.Type(value = KeyboardPart.class, name = "keyboard"),
    @JsonSubTypes.Type(value = MousePart.class, name = "mouse"),
    @JsonSubTypes.Type(value = SpeakersPart.class, name = "speakers"),
    @JsonSubTypes.Type(value = WebcamPart.class, name = "webcam"),
    @JsonSubTypes.Type(value = CaseAccessoryPart.class, name = "case-accessory"),
    @JsonSubTypes.Type(value = CaseFanPart.class, name = "case-fan"),
    @JsonSubTypes.Type(value = FanControllerPart.class, name = "fan-controller"),
    @JsonSubTypes.Type(value = ThermalPastePart.class, name = "thermal-paste"),
    @JsonSubTypes.Type(value = ExternalHardDrivePart.class, name = "external-hard-drive"),
    @JsonSubTypes.Type(value = OpticalDrivePart.class, name = "optical-drive"),
    @JsonSubTypes.Type(value = UpsPart.class, name = "ups")
})
@Data
public abstract class Part {
  private String name;
  private Double price; // Changed to Double to allow null values
  private String type;
}