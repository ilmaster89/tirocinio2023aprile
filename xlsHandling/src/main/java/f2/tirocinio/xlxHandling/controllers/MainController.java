package f2.tirocinio.xlxHandling.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import f2.tirocinio.xlxHandling.daos.CorsoDao;
import f2.tirocinio.xlxHandling.daos.StudenteDao;
import f2.tirocinio.xlxHandling.models.Corso;
import f2.tirocinio.xlxHandling.models.Studente;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {

	@Autowired
	CorsoDao cDao;
	@Autowired
	StudenteDao sDao;

	@GetMapping("/insertAll")
	public String insertAll() {

		try {
			Workbook wb = WorkbookFactory.create(new File("studenti.xlsx"));
			Sheet s = wb.getSheetAt(0);

			for (int i = 1; i <= s.getLastRowNum(); i++) {

				Row r = s.getRow(i);
				String nome = r.getCell(0).getStringCellValue();
				String cognome = r.getCell(1).getStringCellValue();
				Integer corsoId = (int) r.getCell(2).getNumericCellValue();
				Corso c = cDao.findById(corsoId).get();

				Studente student = new Studente();
				student.setCognome(cognome);
				student.setNome(nome);
				student.setCorso(c);
				sDao.save(student);

			}
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	@GetMapping("/select")
	public String select(Model model) {
		model.addAttribute("corsi", cDao.findAll());
		return "select";
	}

	@GetMapping("/download/{corsoId}")
	public String download(@PathVariable Integer corsoId, HttpServletResponse response) {

		Workbook wb = new XSSFWorkbook();
		List<Studente> studenti = sDao.studentiCorso(corsoId);
		List<String> header = Arrays.asList("Nome", "Cognome");

		Sheet s = wb.createSheet("Foglio 1");
		Row headerRow = s.createRow(0);
		headerRow.createCell(0).setCellValue(header.get(0));
		headerRow.createCell(1).setCellValue(header.get(1));

		for (int i = 1; i <= studenti.size(); i++) {
			Row r = s.createRow(i);
			System.out.println(studenti.get(i - 1).getCognome());
			r.createCell(0).setCellValue(studenti.get(i - 1).getNome());
			r.createCell(1).setCellValue(studenti.get(i - 1).getCognome());
		}

		try {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"listaStudenti.xlsx\"");
			wb.write(response.getOutputStream());
			wb.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
	}
}
