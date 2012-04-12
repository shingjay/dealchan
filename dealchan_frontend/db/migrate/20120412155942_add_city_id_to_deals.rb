class AddCityIdToDeals < ActiveRecord::Migration
  def change
    add_column :deals, :city_id, :integer

  end
end
