class RemoveCityFromDeals < ActiveRecord::Migration
  def up
    remove_column :deals, :city
      end

  def down
    add_column :deals, :city, :string
  end
end
