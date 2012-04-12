class RemoveCategoryFromDeals < ActiveRecord::Migration
  def up
    remove_column :deals, :category
      end

  def down
    add_column :deals, :category, :string
  end
end
