package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienAsuransiModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.AsuransiService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {
    @Autowired
    private PasienService pasienService;

    @Autowired
    private AsuransiService asuransiService;

    //beranda
    @RequestMapping("/")
    public String viewAllPasien(Model model){
        //ambil semua objek PasienModel yang ada
        List<PasienModel> listPasien = pasienService.getPasienList();
        //add model pasien untuk dirender
        model.addAttribute("listPasien", listPasien);
        return "beranda";

    }

    //add pasien
    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String addPasienForm(Model model){
        PasienModel pasien = new PasienModel();
        EmergencyContactModel emergencyContact = new EmergencyContactModel();
        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();
        model.addAttribute("listAsuransi", listAsuransi);
        model.addAttribute("emergencyContact", emergencyContact);
        model.addAttribute("pasien", pasien);
        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/tambah/berhasil", method = RequestMethod.POST)
    public String submitPasien(@ModelAttribute PasienModel pasien, @ModelAttribute EmergencyContactModel emergencyContact, Model model){
        pasien.buatKode(pasien);
        pasien.setEmergencyContact(emergencyContact);
        pasienService.addPasien(pasien);
        model.addAttribute("pasien", pasien);
        return "add-pasien";
    }

    @RequestMapping(value="/pasien/tambah", params={"tambahAsuransi"}, method = RequestMethod.POST)
    public String tambahAsuransi(PasienModel pasien, PasienAsuransiModel asuransi, BindingResult bindingResult, Model model) {
        if (pasien.getListPasienAsuransi() ==  null) {
            pasien.setListPasienAsuransi(new ArrayList<PasienAsuransiModel>());
        }
        pasien.getListPasienAsuransi().add(asuransi);
        model.addAttribute("asuransi", asuransi);
        return "add-pasien";
    }

    @RequestMapping(value="/pasien", method = RequestMethod.GET)
    public String viewPasien(
            @RequestParam(value = "nikPasien") String nikPasien, Model model
    ){
        PasienModel pasien = pasienService.getPasienByNikPasien(nikPasien).get();
        model.addAttribute("pasien", pasien);
        return "view-pasien";
    }

//    //lihat pasien berdasarkan NIK pasien
//    @RequestMapping(path = "/pasien?nikPasien={NIKPasien}")
//    public String viewPasien(
//            @RequestParam(value="NIKPasien") String NIKPasien, Model model
//    ){
//        PasienModel pasien = pasienService.getPasienByNIKPasien(NIKPasien).get();
//        model.addAttribute("pasien", pasien);
//        return "view-pasien";
//    }
}
