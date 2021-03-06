DealchanFrontend::Application.routes.draw do

  root :to => 'pages#category' # matches root and routes it to the index action in the Pages controller.
  get '/category' => 'pages#category', :as => "category"
  get '/price' => 'pages#price', :as => "price"

  # 
  # api/v0/deals/by_price_range.json?min_price=10&max_price=60&page=1&city=1
  # api/v0/deals/by_category.json?page=1&city=1&category=6
  scope 'api/v0', :controller => :apis do
    get "/deals/by_five(.:format)" => 'deals#get_deals_by_five'
    get "/deals/by_price_range(.:format)" => 'deals#get_deals_by_price_range'
    get "/deals/by_category(.:format)" => 'deals#get_deals_by_category'
    get "/deals/by_id(.:format)" => 'deals#get_deal_by_id'
    get "/cities/list(.:format)" => 'cities#list_cities'
  end


  # The priority is based upon order of creation:
  # first created -> highest priority.

  # Sample of regular route:
  #   match 'products/:id' => 'catalog#view'
  # Keep in mind you can assign values other than :controller and :action

  # Sample of named route:
  #   match 'products/:id/purchase' => 'catalog#purchase', :as => :purchase
  # This route can be invoked with purchase_url(:id => product.id)

  # Sample resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Sample resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Sample resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Sample resource route with more complex sub-resources
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', :on => :collection
  #     end
  #   end

  # Sample resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end

  # You can have the root of your site routed with "root"
  # just remember to delete public/index.html.
  # root :to => 'welcome#index'

  # See how all your routes lay out with "rake routes"

  # This is a legacy wild controller route that's not recommended for RESTful applications.
  # Note: This route will make all actions in every controller accessible via GET requests.
  # match ':controller(/:action(/:id))(.:format)'
end
