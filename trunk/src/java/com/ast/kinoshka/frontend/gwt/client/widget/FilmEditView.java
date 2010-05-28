package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.event.CategoryChangeEvent;
import com.ast.kinoshka.frontend.gwt.event.LoadedEvent;
import com.ast.kinoshka.frontend.gwt.event.LoadingEvent;
import com.ast.kinoshka.frontend.gwt.event.MessageEvent;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.shared.EditService;
import com.ast.kinoshka.frontend.gwt.shared.EditServiceAsync;
import com.ast.kinoshka.frontend.gwt.utils.DataReceivingCallback;
import com.ast.kinoshka.frontend.gwt.utils.Messages;
import com.ast.kinoshka.frontend.gwt.utils.MessagesBundle;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.ast.kinoshka.frontend.gwt.utils.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;

import java.util.ArrayList;

/**
 * Film edit view.
 * @author Aleh_Stsiapanau
 */
public class FilmEditView extends Composite {

  private static FilmEditBoxUiBinder uiBinder = GWT.create(FilmEditBoxUiBinder.class);
  interface FilmEditBoxUiBinder extends UiBinder<Widget, FilmEditView> {
  }

  private EditServiceAsync dataService = (EditServiceAsync) GWT.create(EditService.class);
  private MessagesBundle messages = Messages.get();
  private HandlerManager eventBus;
  private FilmInfo film;
  private FileUpload uploadImage;
  private GwtEvent<?> backEvent;
  private AttributeListManager genreManager, actorManager, directorManager, countryManager;

  // Counter to control that all components are loaded.
  private int itemsToLoad = 0;

  @UiField
  FlowPanel formPanel;

  //@UiField
  FormPanel uploadForm;

  @UiField
  Image image;
  @UiField
  TextBox name;
  @UiField
  TextBox originalName;
  @UiField
  TextBox year;
  @UiField
  TextBox time;
  @UiField
  TextBox box;
  @UiField
  TextBox disk;
  @UiField
  TextArea description;

  @UiField
  FlowPanel directorPanel;
  @UiField
  FlowPanel genrePanel;
  @UiField
  FlowPanel actorPanel;
  @UiField
  FlowPanel countryPanel;

  @UiField
  Anchor addGenre;
  @UiField
  Anchor addDirector;
  @UiField
  Anchor addActor;
  @UiField
  Anchor addCountry;

  @UiField
  Anchor newGenre;
  @UiField
  Anchor newDirector;
  @UiField
  Anchor newActor;

  @UiField
  ListBox directorList;
  @UiField
  ListBox genreList;
  @UiField
  ListBox actorList;
  @UiField
  ListBox countryList;

  @UiField
  Button save;
  @UiField
  Button cancel;
  @UiField
  Button delete;

  /**
   * This class binds together all control that are responsible for handling specific attribute. 
   * @author Aleh_Stsiapanau
   */
  private class AttributeListManager {
    Category category;
    Anchor newButton;
    Anchor addButton;
    ListBox list;
    FlowPanel panel;
    ArrayList<FilmAttributeInfo> dictionary = new ArrayList<FilmAttributeInfo>();

    public AttributeListManager(Category category, Anchor addButton, Anchor newButton,
        ListBox list, FlowPanel panel) {
      this.category = category;
      this.addButton = addButton;
      this.newButton = newButton;
      this.list = list;
      this.panel = panel;

      // bind controls
      this.addButton.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          selectListItem();
        }
      });

      list.addChangeHandler(new ChangeHandler() {
        @Override
        public void onChange(ChangeEvent event) {
          selectListItem();
        }
      });

      if (this.newButton != null) {
        this.newButton.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            new NewCategoryItemPopup(
                (Window.getClientWidth() / 2),
                AttributeListManager.this.newButton.getAbsoluteTop()) {
              @Override
              public void doSave() {
                if (!StringUtil.isEmpty(value.getText())) {
                  FilmEditView.this.eventBus.fireEvent(new LoadingEvent());

                  dataService.addAttribute(
                      AttributeListManager.this.category, value.getText(),
                      new DataReceivingCallback<FilmAttributeInfo>(FilmEditView.this.eventBus) {
                        @Override
                        public void processResult(FilmAttributeInfo result) {
                          dictionary.add(result);
                          AttributeListManager.this.addListItemToPanel(
                              result.getName(),
                              result.getId(),
                              AttributeListManager.this.list,
                              AttributeListManager.this.panel);
                          FilmEditView.this.eventBus.fireEvent(MessageEvent.info(messages.added(result.getName())));
                        }
                      });
                }
              }
            };
          }
        });
      }
    }

    private void selectListItem() {
      int selected = AttributeListManager.this.list.getSelectedIndex();
      if (selected >= 0) {
        String value = AttributeListManager.this.list.getValue(selected);
        String text = AttributeListManager.this.list.getItemText(selected);

        final RemovableItem item = new RemovableItem(text, value);
        AttributeListManager.this.panel.add(item);
        item.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            AttributeListManager.this.panel.remove(item);
            AttributeListManager.this.list.addItem(item.getName(), item.getValue());
          }
        });

        AttributeListManager.this.list.removeItem(selected);
      }
    }

    private final void addListItemToPanel(final String text, final String value,
        final ListBox list, final FlowPanel container) {

      final RemovableItem item = new RemovableItem(text, value);
      container.add(item);
      item.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          container.remove(item);
          list.addItem(item.getName(), item.getValue());
        }
      });
    }

    /**
     * Binds film attributes to corresponding controls.
     * @param filmAttributes film attributes to bind
     */
    public void bindAttributes(ArrayList<FilmAttributeInfo> filmAttributes) {
      for (FilmAttributeInfo attribute : dictionary) {
        if (filmAttributes.contains(attribute)) {
          addListItemToPanel(attribute.getName(), attribute.getId(), list, panel);
        } else {
          list.addItem(attribute.getName(), attribute.getId());
        }
      }
    }

    public ArrayList<FilmAttributeInfo> getAttributes() {
      ArrayList<FilmAttributeInfo> attributes = new ArrayList<FilmAttributeInfo>();
      for (int i=0; i<panel.getWidgetCount(); i++) {
        RemovableItem item = (RemovableItem)panel.getWidget(i);
        attributes.add(new FilmAttributeInfo(item.getValue(), item.getName()));
      }
      return attributes;
    }

    /**
     * Clears all complex controls.
     */
    public void clear() {
      list.clear();
      panel.clear();
      dictionary.clear();
    }

    /**
     * Loads component dictionary.
     */
    public void loadDictionary() {
      // load attributes dictionary
      itemsToLoad++;
      dataService.getAttributeDictionary(this.category,
          new AsyncCallback<ArrayList<FilmAttributeInfo>>() {
            @Override
            public void onSuccess(ArrayList<FilmAttributeInfo> result) {
              AttributeListManager.this.dictionary.addAll(result);
              checkIfReady();
            }

            @Override
            public void onFailure(Throwable caught) {
              FilmEditView.this.eventBus.fireEvent(MessageEvent.error(messages
                  .failLoadDictionary()));
              checkIfReady();
            }
          });
    }
  }

  public FilmEditView(HandlerManager eventBus) {
    initWidget(uiBinder.createAndBindUi(this));

    this.eventBus = eventBus;

    genreManager = new AttributeListManager(Category.GENRE, addGenre, newGenre,
        genreList, genrePanel);
    actorManager = new AttributeListManager(Category.ACTOR, addActor, newActor,
        actorList, actorPanel);
    directorManager = new AttributeListManager(Category.DIRECTOR, addDirector, newDirector,
        directorList, directorPanel);
    countryManager = new AttributeListManager(Category.COUNTRY, addCountry, null,
        countryList, countryPanel);

    cancel.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doCancel();
      }
    });

    save.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doSave();
      }
    });

    delete.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dataService.deleteFilm(film.getId(), film.getImageName(), new AsyncCallback<Void>() {
          @Override
          public void onSuccess(Void result) {
            FilmEditView.this.eventBus.fireEvent(MessageEvent
                .info(messages.removed(film.getName())));
            FilmEditView.this.eventBus.fireEvent(new CategoryChangeEvent(Category.ALL));
          }

          @Override
          public void onFailure(Throwable caught) {
            FilmEditView.this.eventBus.fireEvent(MessageEvent.error(messages.failDeleting(film
                .getName())));
          }
        });
      }
    });

    reattachUploadForm();
  }

  /**
   * Re-attaches upload form to page.
   */
  private void reattachUploadForm() {
    if (uploadForm != null) {
      uploadForm.removeFromParent();
    }

    uploadForm = new FormPanel();
    uploadForm.setAction("uploadService");
    uploadForm.setMethod(FormPanel.METHOD_POST);
    uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
    uploadForm.addSubmitCompleteHandler(new SubmitCompleteHandler() {
      @Override
      public void onSubmitComplete(SubmitCompleteEvent event) {
        String imageName = parseTmpImage(event.getResults());
        if (imageName != null) {
          FilmEditView.this.film.setUploaded(true);
          image.setUrl(ResourcesUtil.tmpImg(imageName));
        } else {
          FilmEditView.this.eventBus.fireEvent(MessageEvent.error(messages.failUploading()));
        }
      }
    });

    uploadImage = new FileUpload();
    uploadImage.setName("imageFile");
    uploadForm.add(uploadImage);

    uploadImage.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        String file = uploadImage.getFilename();
        if (file != null && file.length() > 0) {
          uploadForm.submit();
        }
      }
    });

    formPanel.add(uploadForm);
  }

  private String parseTmpImage(String raw) {
    String result = null;
    if (raw.contains("tmpfile:")) {
      result = raw.substring(raw.lastIndexOf("tmpfile:") + "tmpfile:".length(),  raw.length());
      if (result.contains("<")) {
        result = result.substring(0, result.indexOf("<")).trim();
      }
    }
    return result;
  }

  /**
   * Saves film data.
   */
  protected void doSave() {

    film.setActors(actorManager.getAttributes());
    film.setGenres(genreManager.getAttributes());
    film.setDirectors(directorManager.getAttributes());
    film.setCountries(countryManager.getAttributes());

    if (validateForm()) {
      eventBus.fireEvent(new LoadingEvent());
      dataService.saveFilm(film, new DataReceivingCallback<Integer>(eventBus) {
        @Override
        public void processResult(Integer result) {
          FilmEditView.this.film.setId(result);
          FilmEditView.this.film.setUploaded(false);
          FilmEditView.this.delete.setEnabled(true);
          FilmEditView.this.eventBus.fireEvent(MessageEvent.info(messages.saved(film.getName())));
        }
      });
    }
  }

  /**
   * Cancel editing.
   */
  protected void doCancel() {
    eventBus.fireEvent(backEvent);
  }

  /**
   * Start editing of specified film item, if film item is null then creates new film.
   * @param filmToEdit film to edit or <code>null</code> if need to create new one
   */
  public void startEditing(FilmInfo filmToEdit) {
    this.film = filmToEdit;
    clearForm();

    if (filmToEdit == null) {
      this.film = new FilmInfo();
      delete.setEnabled(false);
    } else {
      delete.setEnabled(true);
    }
    loadDictionaries();
    reattachUploadForm();
  }

  /**
   * Sets back button target.
   * @param target
   */
  public void setBackAction(GwtEvent<?> target) {
    this.backEvent = target;
  }

  protected boolean validateForm() {
    boolean isValid = true;
    if (StringUtil.isEmpty(film.getName())) {
      isValid = false;
      eventBus.fireEvent(MessageEvent.warning(messages.requiredName()));
    }
    // TODO: validate numeric fields and set focus
    return isValid;
  }

  /**
   * Empty all form fields.
   */
  protected void clearForm() {
    image.setUrl(ResourcesUtil.filmImg(null));
    name.setText(null);
    originalName.setText(null);
    description.setText(null);
    disk.setText(null);
    box.setText(null);
    year.setText(null);
    time.setText(null);

    genreManager.clear();
    actorManager.clear();
    directorManager.clear();
    countryManager.clear();
  }

  /**
   * Bind form field to film to edit data.
   */
  protected void bindForm() {
    image.setUrl(ResourcesUtil.filmImg(film.getImageName()));
    name.setText(film.getName());
    name.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        film.setName(name.getText());
      }
    });

    originalName.setText(film.getOriginalName());
    originalName.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        film.setOriginalName(originalName.getText());
      }
    });

    description.setText(film.getDescription());
    description.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        film.setDescription(description.getText());
      }
    });

    bindNumericField(disk, film.getDisk());
    disk.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        try {
          film.setDisk(Integer.valueOf(disk.getText()));
        } catch (NumberFormatException e) {
          eventBus.fireEvent(MessageEvent.warning(messages.numericField(disk.getText())));
          disk.selectAll();
        }
      }
    });

    bindNumericField(box, film.getBox());
    box.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        try {
          film.setBox(Integer.valueOf(box.getText()));
        } catch (NumberFormatException e) {
          eventBus.fireEvent(MessageEvent.warning(messages.numericField(box.getText())));
          box.selectAll();
        }
      }
    });

    bindNumericField(year, film.getYear());
    year.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        try {
          film.setYear(Integer.valueOf(year.getText()));
        } catch (NumberFormatException e) {
          eventBus.fireEvent(MessageEvent.warning(messages.numericField(year.getText())));
          year.selectAll();
        }
      }
    });

    bindNumericField(time, film.getTime());
    time.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        try {
          film.setTime(Integer.valueOf(time.getText()));
        } catch (NumberFormatException e) {
          eventBus.fireEvent(MessageEvent.warning(messages.numericField(time.getText())));
          time.selectAll();
        }
      }
    });

    genreManager.bindAttributes(film.getGenres());
    actorManager.bindAttributes(film.getActors());
    directorManager.bindAttributes(film.getDirectors());
    countryManager.bindAttributes(film.getCountries());
  }

  private void bindNumericField(HasText control, Integer value) {
    if (value != null) {
      control.setText(value.toString());
    } else {
      control.setText(null);
    }
  }

  private final void checkIfReady() {
    itemsToLoad --;
    if (itemsToLoad <= 0) {
      onLoaded();
    }
  }

  /**
   * Widget loaded event.
   */
  private void onLoaded() {
    bindForm();
    eventBus.fireEvent(new LoadedEvent());
  }

  /**
   * Load widget service data.
   */
  private void loadDictionaries() {
    genreManager.loadDictionary();
    actorManager.loadDictionary();
    directorManager.loadDictionary();
    countryManager.loadDictionary();
  }

  // TODO: refactoring
}
