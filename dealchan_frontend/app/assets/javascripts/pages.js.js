(function() {

  jQuery(function() {
    var category_id, category_name, deal_categories;
    if (!$.cookie('selected_location')) {
      Dealchan.Layouts.CityAskModal.cityAskModal("#city-selection-modal");
    }
    $("#city-selection-modal").trigger('click');
    deal_categories = {
      "1": "Activities & Events",
      "2": "Food & Drinks",
      "3": "Health & Beauty",
      "4": "Shopping & Services",
      "5": "Travel",
      "6": "Miscellaneous"
    };
    for (category_id in deal_categories) {
      category_name = deal_categories[category_id];
      Dealchan.Pages.DealContainer.initializeDeals($("#deals-container"), category_id, category_name, function(category_id, category_name) {
        $("#showMore-" + category_id).on('click', {
          cat: category_id
        }, function(event) {
          console.log('push to show more');
          return Dealchan.Layouts.DealContainer.showMoreDeals($("#dealContainer-" + event.data.cat), "/api/v0/deals/by_category.json?page=3&city=3&category=" + event.data.cat, category_id, category_name);
        });
        return $("#jump-to-" + category_id).on('click', {
          cat: category_id
        }, function(event) {
          console.log("jump to cat");
          console.log(event.data.cat);
          return $('html,body').animate({
            scrollTop: $("#dealContainer-" + event.data.cat).offset().top - 120
          }, 600);
        });
      });
    }
    return $('#submit-location').on('click', function() {
      $.cookie('selected_location', $('#location-dropdown').val(), {
        expires: 1200
      });
      return $.fancybox.close();
    });
  });

}).call(this);
