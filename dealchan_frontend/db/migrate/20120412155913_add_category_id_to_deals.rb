class AddCategoryIdToDeals < ActiveRecord::Migration
  def change
    add_column :deals, :category_id, :integer

  end
end
