package net.ent.etrs.megamovies.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableSetValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;
import net.ent.etrs.megamovies.view.converter.GenreConverter;
import net.ent.etrs.megamovies.view.converter.LocalDateConverter;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;
import net.ent.etrs.megamovies.view.utils.Screens;

import java.time.LocalDate;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListerFilmsController extends AbstractController {
	final ObservableList<Film> oLstFilms = FXCollections.observableArrayList();
	@FXML
	public VBox barreMenu;
	@FXML
	public TableView tvFilms;
	@FXML
	public TextField tfRecherche;
	@FXML
	public ChoiceBox<Film> cbRecherche;
	@FXML
	public TableColumn<Film, String> tcTitre;
	@FXML
	public TableColumn<Film, LocalDate> tcDateSortie;
	@FXML
	public TableColumn<Film, Genre> tcGenre;
	
	
	FilteredList<Film> filmFilteredList = new FilteredList<>(oLstFilms);
	
	public void initialize() {
		rechargerListeFilms();
		
		initFilteredLstFilms();
		
		chargerTvFilms();
	}
	
	private void chargerTvFilms() {
		tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
		tcDateSortie.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
		tcDateSortie.setCellFactory((param) -> new TextFieldTableCell<>(new LocalDateConverter()));
		
		tcGenre.setCellValueFactory((param) -> new ObservableSetValue<Genre>(new GenreConverter().toString()) {
		});
		
		this.tvFilms.setRowFactory(tv -> creerRow((TableView<Film>) tv));
	}
	
	private TableRow<Film> creerRow(TableView<Film> tv) {
		
		TableRow<Film> row = new TableRow<>();
		
		row.emptyProperty().addListener((observable, wasEmpty, isEmpty) -> {
			if (isEmpty) {
				row.setContextMenu(null);
			} else {
				row.setContextMenu(creerContextMenu());
			}
		});
		return row;
	}
	
	private ContextMenu creerContextMenu() {
		ContextMenu contextMenu = new ContextMenu();
		
		MenuItem itemModifier = new MenuItem("Modifier");
		MenuItem itemSupprimer = new MenuItem("Supprimer");
		itemModifier.setOnAction(c -> modifier());
		itemSupprimer.setOnAction(c -> supprimer());
		contextMenu.getItems().add(itemModifier);
		contextMenu.getItems().add(itemSupprimer);
		
		return contextMenu;
	}
	
	private void supprimer() {
		
		try {
			FACADE_FILM.delete((Film) this.tvFilms.getSelectionModel().getSelectedItem());
			this.chargerlistFilms();
		} catch (BusinessException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
		}
		
	}
	
	private void chargerlistFilms() {
		try {
			this.oLstFilms.clear();
			this.oLstFilms.addAll(FACADE_FILM.findAll());
		} catch (BusinessException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
		}
	}
	
	
	private void modifier() {
		FxmlUtils.chargerScene(Screens.FILMS, new GererFilmController());
	}
	
	private void initFilteredLstFilms() {
		this.filmFilteredList = new FilteredList<>(oLstFilms, s -> true);
		tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filmFilteredList.setPredicate(contact -> {
				boolean filtre = predicatFiltreLstFilms(newValue, contact);
				return filtre;
			});
		});
	}
	
	private boolean predicatFiltreLstFilms(String newValue, Film film) {
		if (newValue == null || newValue.isEmpty()) {
			return true;
		}
		String lowerCaseFilter = newValue.toLowerCase();
		return film.getTitre().toLowerCase().contains(lowerCaseFilter);
	}
	
	private void rechargerListeFilms() {
		try {
			this.oLstFilms.clear();
			this.oLstFilms.addAll(FACADE_FILM.findAll().stream().sorted((c1, c2) -> comparerFilms(c1, c2))
					.collect(Collectors.toList()));
		} catch (BusinessException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
		}
	}
	
	private int comparerFilms(Film c1, Film c2) {
		int retour;
		retour = c1.getTitre().toLowerCase().compareTo(c2.getTitre().toLowerCase());
		if (retour == 0) {
			retour = c1.getDateSortie().compareTo(c2.getDateSortie());
		}
		return retour;
	}
	
	private void initTfRecherche() {
		this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			this.filmFilteredList.setPredicate(titre -> predicatFiltreLstFilms(newValue, titre));
		});
		
	}
}
